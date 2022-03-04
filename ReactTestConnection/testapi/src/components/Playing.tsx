import { useNavigate } from "react-router-dom";
import { useState,useEffect, MouseEvent } from "react";
import axios from "axios";
import { DataStore,  useDataStore } from "../Store/DataStore";

// css
import styles from '../CSSstyle/playing.module.css'

// image 
import BgPlaying from '../Images/BgGameplay.png'
import woodBox from '../Images/woodBox.png'
import virus1 from '../Images/woodBoxGreenVirus.png'
import virus2 from '../Images/woodBoxRedVirus.png'
import virus3 from '../Images/woodBoxBlueVirus.png'
import antibody1 from '../Images/woodBoxGreenAntibody.png'
import antibody2 from '../Images/woodBoxYellowAntibody.png'
import antibody3 from '../Images/woodBoxBlueAntibody.png'

import { TransformComponent, TransformWrapper } from "react-zoom-pan-pinch";
import { useShopStore } from "../Store/ShopStore";
import Shop, { updatestatusShop } from "./Shop";
import { AntibodyStore, postAntibody, useAntibodyStore } from "../Store/AntibodyStore";
import AntibodyController, { updateAntibodyController } from "./AntibodyController";
import { useAntibodyControllerStore } from "../Store/AntibodyControllerStore";
import DropbarMenu from "./DropbarMenu";
import StatusBar from "./StatusBar";
import Loading from "./Loading";
import GameSpeed from "./GameSpeed";




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
    virus_amount:number,
    antibody_amount:number
}
export type TimerType={
      time_count: number,
        timePass: number,
        speed_multiplier: number
}
export type OrganismType = {
        id:string,
        category:string,
        type:number,
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
    const shopStore = useShopStore()
    const controllerStore = useAntibodyControllerStore()

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

     // background
      useEffect(() => {
        const bg = document.querySelector('body')
        if(loading){
            if (bg) bg.style.cssText = `background: #1e1e1e no-repeat fixed; width: 100%;overflow: hidden;`
        }
        else{
            if (bg) bg.style.cssText = `background: url(${BgPlaying}) no-repeat center center fixed;
            height: 100%;
             background-size:cover;
             overflow: hidden;`
        }
        
    },[loading])

   

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
     
            s.max_x = data.dimension[0]
            s.max_y = data.dimension[1]

            s.timer = data.timer
            s.credit = data.credit
            s.gameState = data.gameState
            s.virus_amount = data.virus_amount
            s.antibody_amount = data.antibody_amount
            
        })
        }
    },[data])

    //return picture src of each type of organism 
    const decoder = (key : string,type:number) => {
            if(key==="Virus"){
                switch(type){
                    case 1: return virus1
                    case 2: return virus2
                    case 3: return virus3
                }
            }
            if(key==="Antibody"){
                switch(type){
                    case 1: return antibody1
                    case 2: return antibody2
                    case 3: return antibody3
                }
            }
    }
    const removeBlood = (Id:string)=>{
        const object = document.getElementById(Id)
        if (object) {
            while (object.lastElementChild!==object.children[0]) {
            if(object.lastElementChild!==null){
                 object.removeChild(object.lastElementChild);
            }
            }
        }
    }
    const blood = (currentBlood:number,maxHP:number,max_scale:number,Id:string) => {
        const object = document.getElementById(Id)
        const blood = document.createElement('span')
        const bloodcurrent = document.createElement('span')
    
        if (object) {
        if (object.children[1] != null) 
        {
            object.removeChild(object.children[1]);
        }
          
        blood.classList.add(`${styles.blood}`)
        blood.style.cssText = `position: absolute; width: ${max_scale*0.8}px; height: ${max_scale*0.05}px;`
        let Hp = (currentBlood/maxHP)*100 
        let collorHp = '' 
        if (Hp > 50) {
            collorHp = 'rgb(0, 255, 34)'
        }else if (Hp > 30) {
            collorHp = '#fbbf24'
        }else if (Hp > 15) {
            collorHp = '#f97316'
        }else {
            collorHp = '#ef4444'
        }
        bloodcurrent.style.cssText = `position: absolute; width: 100%; height: 100%; left: ${Hp-100}%; background-color: ${collorHp};`
        blood.appendChild(bloodcurrent)  
      
        if(object.className==="playing_containerBlood__JvxGx")
                object.appendChild(blood)
    }

    }

    //create map with virus and antivirus assign at its position
    const createMap = ()=>{
        const maxX = dataStore.max_x
        const maxY = dataStore.max_y
        //max scale
        let max_scale = 0
        let winWidth = window.innerWidth*0.68
        let winHeight = window.innerHeight*0.65
        let x_scale : number = winWidth/maxX  // 1000
        let y_scale : number = winHeight/maxY   // 660
        if (maxY<maxX) {
            if(y_scale*maxX <= winWidth) max_scale = y_scale
            else max_scale = x_scale
        }else {
            if(x_scale*maxX <= winHeight) max_scale = x_scale
            else max_scale = y_scale
        }



        let organMap:JSX.Element[][] = new Array(maxY) 
        
       
       if(data!==null){
            for(let i = 0;i<maxY;i++){
            organMap[i] = new Array(maxX)
                for(let j = 0;j<maxX;j++ ){
                    const currentId = i.toString()+j.toString() 
                   removeBlood(currentId)
                    organMap[i][j]= <a id={currentId} onDoubleClick={(e:MouseEvent)=>DoubleClickedBlock(e,i,j)}>
                       
                    <img src={woodBox} alt="" style={{
                    position: "relative",
                    width: `${max_scale}px`,
                    height: `${max_scale}px`,
                    margin: 0
                }}/></a>  
                }
                       
        }


        for(let i=0;i<data.allOrgan.length;i++){
        const organ = data.allOrgan[i]
        const x_pos = maxY-1-organ.position[1]
        const y_pos = organ.position[0]
        const currentId:string = x_pos.toString()+y_pos.toString()
        
       
        
        if(organ.category==="Antibody"){ //can select antibody to controll it
            organMap[x_pos][y_pos] =  
            <a id={currentId} onDoubleClick={(e:MouseEvent)=>DoubleClickedAntibody(e,organ.position[0],organ.position[1])}
               className={styles.containerBlood}
            >
                  {
                blood(organ.hp,organ.max_HP,max_scale,currentId)
            }
            <img   
            src={decoder(organ.category,organ.type)} alt="" style={{
         position: "relative",
         width: `${max_scale}px`,
        height: `${max_scale}px`,
         margin: 0
     }}/> </a>
        }
        else {  //virus , can't select
            organMap[maxY-1-organ.position[1]][organ.position[0]] = 
            <a id={currentId}  className={styles.containerBlood}
               >
            {
                blood(organ.hp,organ.max_HP,max_scale,currentId)
            }
            <img src={decoder(organ.category,organ.type)} alt="" style={{
         position: "relative",
         width: `${max_scale}px`,
        height: `${max_scale}px`,
         margin: 0
     }}/> </a>
        }
           
        }
       } 
       
       return  organMap.map((y:JSX.Element[])=>{return <tr>{y.map((x:JSX.Element)=>{return  <td>{x}</td>})}</tr>})
    }

    //double click to select empty block
    const DoubleClickedBlock = (e:MouseEvent,i:number,j:number) =>{  //i j is position in map start at left up corner
        if(!shopStore.shopStatus){
            updatestatusShop([e.pageX,e.pageY],[i,j])
            openshop([i,j])
            AntibodyStore.update(s=>{
                s.targetId = ""
                s.type = 0
                s.location = [j,(dataStore.max_y-1-i)]
                s.genetic = ""
            }
            )
        }
    }
    //double click to select antibody
    const DoubleClickedAntibody = (e:MouseEvent,i:number,j:number) =>{// i j is real position start at left buttom corner
       

        if(data!=null){
           const [selectAntibody] =  data.allOrgan.filter(organ=>(organ.position[0]===i&&organ.position[1]===j))
        AntibodyStore.update(s=>{
            s.targetId = selectAntibody.id
        }) 
       
        if(!controllerStore.status){
            updateAntibodyController([e.pageX,e.pageY],[dataStore.max_y-1-j,i])
            opencontroller([dataStore.max_y-1-j,i])
        }
    }
        
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
  <Shop/>
  <GameSpeed/>
  <StatusBar/>
  <AntibodyController/>
    <div className={styles.containerAll}>
        <div className={styles.container1}>
            <div className={styles.container2}>
                <div className={styles.container3}>
                     <TransformWrapper doubleClick={{
                         disabled:true}}>
                      <TransformComponent>
                         <table className={styles.mytable}>{
                            createMap()
                          }</table>
                     </TransformComponent>
                   </TransformWrapper>
                </div>
            </div>
        </div>
    </div>
    <DropbarMenu/>               
 </div>       
            )   
        }
    }

  

    const openshop = (arr :number[]) => {
        const td = document.querySelectorAll('td')[arr[0]*dataStore.max_x+arr[1]]
        td.style.cssText = "opacity: 0.5;transform: scale(0.85);"
    }
    const opencontroller = (arr :number[]) => {
        const td = document.querySelectorAll('td')[arr[0]*dataStore.max_x+arr[1]]
        td.style.cssText = "opacity: 0.5;transform: scale(0.85);"
    }
 

    return (
        <div>
           
            {
            loading?<Loading/>
                     :
                    render()
            }
        </div>
    )
}

export default Playing;
