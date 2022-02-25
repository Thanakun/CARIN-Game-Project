// Hook
import styles from '../CSSstyle/manu.module.css'
import { useNavigate } from 'react-router-dom'
 
// component
import Logo from './Logo' 
import { useEffect } from 'react'
import { postState } from './Playing'



const MainMenu = () => { 
    let nav = useNavigate()
   
    useEffect(()=>{
        postState("MAIN_MENU")
    },[])

    return (
        <div>
            <Logo/> 
            <div className={styles.container}>
                <a onClick={() => nav('/gameplay')} className={styles.btn}> 
                    <span>Start</span>
                    <div className={styles.bthbefore}></div>
                </a>
            </div>
        </div>
    )
}

export default MainMenu
