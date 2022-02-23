import { useNavigate } from "react-router-dom";
import { useState,useEffect } from "react";
import axios from "axios";
import { DataStore, DataStoreType, initMap } from "../Store/DataStore";

// css
import styles from '../CSSstyle/positionMap.module.css'

// image 
import greenBox from '../Images/greenBox.png'
import redBox from '../Images/redBox.png'



export type GameDataType = {
    timer: TimerType,
    credit:number,
    gameState: string,
    dimension: number[],
    allOrgan:OrganismType[]
}
export type TimerType={
      time_count: number,
        timePass: number
}
export type OrganismType = {
        id:string,
        category:string,
        tye:number,
        hp:number,
        max_HP:number,
        position:number[]
}


const Playing = ()=>{
    let nav = useNavigate()
   // const [data,setData] = useState<GameDataType|null>(null)
    const [loading,setLoading] = useState<boolean>(true)
    const [err,setErr] = useState<boolean>(false)
    const [timepass,setTimepass] = useState<number>(1000)
  
    const dataStore = useDataStore()

    function useDataStore():DataStoreType{
        return DataStore.useState(s=>s)

    }
 

    const fetchApi = async() =>{
        try{
                const resp = await axios.get<GameDataType>('http://localhost:8080/game/get/gameData')
              //  setData(resp.data)
                setLoading(false)
               setTimepass(resp.data.timer.timePass)
              
                //update data store
               DataStore.update((s)=>{
                //update organism
                s.max_x = resp.data.dimension[0]
                s.max_y = resp.data.dimension[1]
                resp.data.allOrgan.map((og)=>{    //add organism to its location in map
                   const pos =  og.position
                   s.organism[pos[0]][pos[1]] = og
                })
    
                //update time
                s.timer = resp.data.timer
                //update credit
                s.credit = resp.data.credit
                //update gamestate
                s.gameState = resp.data.gameState
            })
               
        }
        catch(err){
            setErr(true)
            setLoading(false)
        }
    }



    const clickPause = () =>{
        nav('/pause')
    }

    //set up  fetch
    useEffect(()=>{
        setInterval(()=>{
             setLoading(true)
            fetchApi()
        },timepass)  //fetch every time unit
        //set up map
        }
    ,[])
    //set texture map
    useEffect(()=>{
        if(dataStore.max_x!==-1 && dataStore.max_y!==-1){
            initMap(dataStore.max_x,dataStore.max_y)
        }
    },[dataStore.max_x,dataStore.max_y])


    const createGrid =  (count : number) => {
        const output = []
        for (let i=0; i<count; i++) {
            output.push(i+1)
        }
        return output
    }

    const decoder = (key : string) => {
        switch (key) {
            case 'G' : return greenBox; 
            case 'R' : return redBox;
        }
    }

    const render= () =>{
        if(loading){
            return(
                <div className='text-center space-y-3'>
                <p className='text-2xl'>Loading ...</p>
              </div>  
               
            )
        }
        else if(err)
        {
            return(
                <div className='text-center space-y-3'>
                <p className='text-2xl text-red-500'>There was an error. Please try again later.</p>
              </div>  
            )
        }
        else{  // show map

            const maxX = dataStore.max_x
            const maxY = dataStore.max_y

                return (
                <div>
                    <div>
                        {dataStore.timer.time_count}
                    </div>


                    <div className={styles.container}>
                        <table className={styles.mytable}>{createGrid(maxY).map((i) => {
                            return(
                                <tr>{createGrid(maxX).map((j) => {
                                    
                                    return(
                                        <td style={{margin: "0",padding: "0",}}>{
                                            <img src={decoder(dataStore.textureMap[i-1][j-1])} alt="" style={{
                                                position: "relative",
                                                width: `calc(720px/${maxX > maxY ? maxX : maxY})`,
                                                height: `calc(720px/${maxX > maxY ? maxX : maxY})`,
                                                margin: 0
                                            }}/>}
                                        </td>
                                    )
                                })}</tr>
                            )
                        })}</table>
                    </div>
                    <div>
                        <a onClick={() => clickPause} className={styles.btnpuase}> 
                            <span>PausE</span>
                            <div className={styles.bthbefore}></div>
                        </a>
                    
                    </div>
                </div>
        
            )  
            
          
        }
    }


    return (
        <div>
            <p>Playing</p>
            <button onClick={()=>clickPause()}>pause</button>
            {render()}
        </div>
    )
}

export default Playing;
