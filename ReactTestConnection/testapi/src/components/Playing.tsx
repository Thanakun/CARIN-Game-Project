import { useNavigate } from "react-router-dom";
import { useState,useEffect } from "react";
import axios from "axios";

export type GameDataType = {
    timer: TimerType,
    credit:number,
    gameState: string,
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

    const fetchApi = async() =>{
        try{
                const resp = await axios.get<GameDataType>('http://localhost:8080/game/get/gameData')
                setData(resp.data)
                setLoading(false)
        }
        catch(err){
            setErr(true)
            setLoading(false)
        }
    }

    const clickPause = () =>{
        nav('/pause')
    }

    useEffect(()=>{
        setInterval(()=>{
            fetchApi()
        },1000)
      
    },[])

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
        else{  
            return( 
             <div className='text-center space-y-3'>
                {data?.credit}
                <p>\b</p>
                {data?.gameState}
                <p>\b</p>
                {data?.timer.time_count}
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
