import { useNavigate } from "react-router-dom";

const Pause = ()=>{
    let nav = useNavigate()
    const unPause = () =>{
            nav('/gameplay')
    }
    
    return (
        <div>
            <p>Pause</p>
            <button onClick={()=>unPause()}>unPaused</button>
        </div>
    )
}

export default Pause;