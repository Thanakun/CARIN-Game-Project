// Hook
import styles from '../CSSstyle/manu.module.css'
import { useNavigate } from 'react-router-dom'
 
// component
import Logo from './Logo' 
import { useEffect } from 'react'
import { postState } from './Playing'

// images
import bgHomered from '../Images/bghomered.png'
import bgHomegreen from '../Images/bghome.png'

const MainMenu = () => { 
    let nav = useNavigate()
   
    useEffect(()=>{
        postState("MAIN_MENU")
    },[])

    const BGover = () => {
        const bg = document.querySelector('body')
            if (bg) bg.style.cssText += `background-image: url(${bgHomered}); transition: all 0.8s;`
    }

    const BGleave = () => {
        const bg = document.querySelector('body')
            if (bg) bg.style.cssText += `background-image: url(${bgHomegreen}); transition: all 0.8s;`
    }

    return (
        <div>
            <Logo/> 
            <div className={styles.container}>
            <a onMouseOverCapture={() => {BGover()}} onMouseLeave={() => {BGleave()}} 
            onClick={() => nav('/gameplay')} className={styles.btn}> 
                    <span>Start</span>
                    <div className={styles.bthbefore}></div>
                </a>
            </div>
        </div>
    )
}

export default MainMenu
