import { useNavigate } from "react-router-dom";
import { useState,useEffect } from "react";
import axios from "axios";
import { DataStore, DataStoreType, useDataStore } from "../Store/DataStore";

// css
import styles from '../CSSstyle/positionMap.module.css'

// image 
import greenBox from '../Images/greenBox.png'
import AntibodyPic from '../Images/Red Antigen.png'
import VirusPic from '../Images/Blue Virus.png'




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
    const [data,setData] = useState<GameDataType|null>(null)
    const [loading,setLoading] = useState<boolean>(true)
    const [err,setErr] = useState<boolean>(false)
    const dataStore = useDataStore()

   
 

    const fetchGamedata = async() =>{
        try{
                const resp = await axios.get<GameDataType>('http://localhost:8080/game/get/gameData')
               setData(resp.data)
               setLoading(false)
        }
        catch(err){
            setErr(true)
      
        }
    }



    //set up  fetch for every time unit
    useEffect(()=>{
      const interval =  setInterval(()=>{
            fetchGamedata()
        },dataStore.timer.timePass)  //fetch every time unit
        
        return ()=>{clearInterval(interval)}  //clear serInterval
    }
    ,[dataStore.timer.timePass])

   
    //update Data store every time incoming data change
    useEffect(()=>{
        if(data!=null){
             //update data store
           DataStore.update((s)=>{
            //update organism
            s.max_x = data.dimension[0]
            s.max_y = data.dimension[1]
            //update time
            s.timer = data.timer
            //update credit
            s.credit = data.credit
            //update gamestate
            s.gameState = data.gameState
            //update organism
            s.allOrganism = data.allOrgan
        })
        }
    },[data])


    const decoder = (key : string) => {

        switch (key) {
            case "Virus" : return VirusPic; 
            case "Antibody" : return AntibodyPic;
            default : return greenBox;
        }
    }

    const createMap = ()=>{
        const maxX = dataStore.max_x
        const maxY = dataStore.max_y

        let organMap:JSX.Element[][] = new Array(maxY) 
        
        for(let i = 0;i<maxY;i++){
            organMap[i] = new Array(maxX).fill(
                       <img src={greenBox} alt="" style={{
                    position: "relative",
                    width: `calc(720px/${maxX > maxY ? maxX : maxY})`,
                    height: `calc(720px/${maxX > maxY ? maxX : maxY})`,
                    margin: 0
                }}/>        
            )   
        }

        for(let i=0;i<dataStore.allOrganism.length;i++){
            const organ = dataStore.allOrganism[i]
            organMap[organ.position[0]][organ.position[1]] =  
            <img src={decoder(organ.category)} alt="" style={{
         position: "relative",
         width: `calc(720px/${maxX > maxY ? maxX : maxY})`,
         height: `calc(720px/${maxX > maxY ? maxX : maxY})`,
         margin: 0
     }}/>  
       
        }
       return  organMap.map((y:JSX.Element[])=>{return <tr>{y.map((x:JSX.Element)=>{return  <td style={{margin: "0",padding: "0",}}>{x}</td>})}</tr>})
    }


    const render= () =>{
         if(err)
        {
            return(
                <div className='text-center space-y-3'>
                <p className='text-2xl text-red-500'>There was an error. Please try again later.</p>
              </div>  
            )
        }
        else{  // show map

                return (
                <div>
                    <div>
                        {dataStore.timer.time_count}
                    </div>

                     <div className={styles.container}>
                        <table className={styles.mytable}>{
                            loading?
                            <p>loading...</p>
                            :createMap()
                        }</table>
                    </div> 

                    <div>
                        <button onClick={() => nav('/pause')} className={styles.btnpuase}> 
                            <span>PausE</span>
                            <div className={styles.bthbefore}></div>
                        </button>
                    
                    </div>
                </div>
        
            )  
            
          
        }
    }


    return (
        <div>
            <p>Playing</p>
            {render()}
        </div>
    )
}

export default Playing;
