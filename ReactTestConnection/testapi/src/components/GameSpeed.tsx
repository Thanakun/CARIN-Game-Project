// css
import axios from 'axios'
import { useState } from 'react'
import styles from '../CSSstyle/gamespeed.module.css'
import { useDataStore } from '../Store/DataStore'

type timeReq = {
    command:string,
    value:number
}

const postTime = async(req:timeReq)=>{
    try{
        axios.post<timeReq>('http://localhost:8080/game/input/time',req)
            .then(resp=>console.log(resp.data))
    }
    catch(err){
        console.log(err)
    }
}

const GameSpeed = () => {
    // variables
   const dataStore = useDataStore()

   

    return (
        <div className={styles.container}>
             {
                dataStore.timer.speed_multiplier===0.25?null
                :<div className={styles.minus} onClick={() =>{
                    const req:timeReq={
                     command:"decreaseSpeed",
                     value:0
                 }
                 postTime(req)
             }}><span>-</span></div>
            }
            <div className={styles.scope}><span>x{dataStore.timer.speed_multiplier}</span></div>
            {
                dataStore.timer.speed_multiplier===3?null
                :<div className={styles.plus} onClick={() => {
                const req:timeReq={
                    command:"increaseSpeed",
                    value:0
                }
                postTime(req)
            }}><span>+</span></div>
            }
           
        </div>
    )
}

export default GameSpeed