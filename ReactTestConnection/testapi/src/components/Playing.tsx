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
import { TransformComponent, TransformWrapper } from "react-zoom-pan-pinch";
import { isFloat32Array } from "util/types";


//export type and function

export type postStateType ={
    wanted_state:string
}
export type respStateType = {
    wanted_state: string,
    requestType: string
}


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

export const postState = async( wanted_state:string)=>{
    try{
        const req:postStateType = {
            wanted_state: wanted_state
        }
        axios.post<respStateType>('http://localhost:8080/game/input/state',req)
            .then(resp=>console.log(resp.data.wanted_state))
    }
    catch(err){
        console.log(err)
    }
}

//main playing 


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

   

    //start and resume game when web loaded
    useEffect(()=>{
        if(!loading){  //if loaded complete
            if(dataStore.gameState==="MAIN_MENU"){  //current state is main menu
                 postState("START")
            }
            if(dataStore.gameState==="PAUSE"){    //if current is pause then resume
                postState("PLAYING")  
            }  
            
            //check game result
            if(dataStore.gameState==="WIN" || dataStore.gameState==="LOSE"){
             nav('/end')
                }
            }
       
    },[loading,dataStore.gameState])




    //set up  fetch for every time unit
    useEffect(()=>{
           const interval =  setInterval(()=>{
            fetchGamedata()
        },data?data.timer.timePass:100)  //fetch every time unit 
        
        return ()=>{clearInterval(interval)}  //clear serInterval 
    }
    ,[dataStore.timer.timePass])

   
    //update Data store every time incoming data change
    useEffect(()=>{
        if(data!=null){
             //update data store
           DataStore.update((s)=>{
            //update dimension
            s.max_x = data.dimension[0]
            s.max_y = data.dimension[1]
            //update time
            s.timer = data.timer
            //update credit
            s.credit = data.credit
            //update gamestate
            s.gameState = data.gameState

        })
        }
    },[data])

    //return picture src of each type of organism 
    const decoder = (key : string) => {

        switch (key) {
            case "Virus" : return VirusPic; 
            case "Antibody" : return AntibodyPic;
            default : return greenBox;
        }
    }

    //create map with virus and antivirus assign at its position
    const createMap = ()=>{
        const maxX = dataStore.max_x
        const maxY = dataStore.max_y
        //max scale
        let max_scale = 0
    let x_scale : number = 1000/maxX
    let y_scale : number = 660/maxY
    if (maxY<maxX) {
    if(y_scale*maxX <= 1000) max_scale = y_scale
    else max_scale = x_scale
    }else {
    if(x_scale*maxX <= 660) max_scale = x_scale
    else max_scale = y_scale
    }

        let organMap:JSX.Element[][] = new Array(maxY) 
        
        for(let i = 0;i<maxY;i++){
            organMap[i] = new Array(maxX).fill(
                       <img src={greenBox} alt="" style={{
                    position: "relative",
                    width: `${max_scale}px`,
                    height: `${max_scale}px`,
                    margin: 0
                }}/>        
            )   
        }

       if(data!==null){
        for(let i=0;i<data.allOrgan.length;i++){
        const organ = data.allOrgan[i]
            organMap[organ.position[0]][organ.position[1]] =  
            <img src={decoder(organ.category)} alt="" style={{
         position: "relative",
         width: `${max_scale}px`,
        height: `${max_scale}px`,
         margin: 0
     }}/>  
        }
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
              
                <div className={styles.containerAll}>
        <div className={styles.container1}>
            <div className={styles.container2}>
                <div className={styles.container3}>
                     <TransformWrapper>
                      <TransformComponent>
                         <table className={styles.mytable}>{
                            createMap()
                          }</table>
                     </TransformComponent>
                   </TransformWrapper>
                </div>
            </div>
        </div>
        <div>
            <a onClick={() => pauseClick()} className={styles.btnpuase}> 
                <span>Pause</span>
                    <div className={styles.bthbefore}></div>
            </a>
        </div>
    </div>               
        
            )   
        }
    }

    const pauseClick = () => {
        nav("/pause")
        postState("PAUSE")
        DataStore.update(s=>{
            s.gameState = "PAUSE"
        })
    }
 

    return (
        <div>
           
            {
            loading?<div>
                    <p>loading...</p>
                     </div> 
                     :
                     <div>
                           <div>{dataStore.timer.time_count}</div>
                           {render()}
                     </div>    
            }
        </div>
    )
}

export default Playing;
