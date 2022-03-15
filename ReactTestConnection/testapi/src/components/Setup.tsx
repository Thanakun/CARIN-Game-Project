import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import { AntibodyStoreType, postAntibody } from "../Store/AntibodyStore"
import { postState } from './Playing'

const Setup = ()=>{
    const [genetic,setGenetic] = useState<string>("")
    const nav = useNavigate()

    useEffect(()=>{
        postState("SETUP")
    },[])

    const postGenetic = (genetic:string) =>{
        const req:AntibodyStoreType={
            targetId:"",
            type:0,
            location:[0,0],
            cmd:"setup",
            genetic:genetic
        }
        postAntibody(req)
        nav("/gameplay")
    }

    return(
        <div>
            <button onClick={e=>nav("/")}>Back</button>
            <form>
                {/* <input type="text" name="genetic" placeholder="Enter genetic code" onChange={(e)=>{setGenetic(e.target.value)}} ></input> */}
                <form>
                     <textarea  name="genetic" placeholder="Enter genetic code..." onChange={(e)=>{setGenetic(e.target.value)}}></textarea>
                </form>
                <button type="button" onClick={e=>{postGenetic("default")}}>use default</button>
                <button type="button" onClick={e=>postGenetic(genetic)}>submit</button>
            </form>
        </div>
    )
}

export default Setup