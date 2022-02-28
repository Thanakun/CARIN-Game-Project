
import { useEffect } from "react"
import { useNavigate } from "react-router-dom"
import { DataStore, useDataStore } from "../Store/DataStore"
import { postState } from "./Playing"
import victory from '../Images/victory.png'
import lose from "../Images/defeat.png"


const End = ()=>{
    const dataStore = useDataStore()
    const nav = useNavigate()

    useEffect(() => {
        const bg = document.querySelector('body')
      
        if(dataStore.gameState==="WIN"){
              if (bg) bg.style.cssText = `background: url(${victory}) no-repeat fixed; width: 100%;overflow: hidden;`
        }
          if(dataStore.gameState==="LOSE"){
            if (bg) bg.style.cssText = `background: url(${lose}) no-repeat fixed; width: 100%;`  
          }
      
          
    })
    
   

    const restartClick = ()=>{
            nav('/')
    }

    return(
        <div>
            <button onClick={()=>restartClick()}>go to main menu</button>
        </div>
    )
}

export default End