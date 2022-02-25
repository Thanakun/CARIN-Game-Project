
import { useNavigate } from "react-router-dom"
import { DataStore, useDataStore } from "../Store/DataStore"
import { postState } from "./Playing"


const End = ()=>{
    const dataStore = useDataStore()
    const nav = useNavigate()
    
    const render=()=>{
        if(dataStore.gameState==="WIN"){
            return (
                <p>You Win</p>
            )
        }
        else if(dataStore.gameState==="LOSE"){
            return(
                <p>You Lose</p>
            )
        }
        else{
            return(
                <p>wrong format state</p>
            )
        }
    }

    const restartClick = ()=>{
            nav('/')
            postState("MAIN_MENU")
            DataStore.update(s=>{
                s.gameState = "MAIN_MENU"
            })
    }

    return(
        <div>
            {render()}
            <button onClick={()=>restartClick()}>go to main menu</button>
        </div>
    )
}

export default End