import { useNavigate } from "react-router-dom";


const MainMenu = ()=>{
    let nav = useNavigate()

    const onclick = () =>{
          nav("/gameplay")
    }
    return (
        <div>
            <p>Main menu</p>
        <button onClick={()=>{ onclick()}} >Start</button>
        </div>
    )
}

export default MainMenu;