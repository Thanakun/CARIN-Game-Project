// Hook
import styles from '../CSSstyle/manu.module.css'
import { useNavigate } from 'react-router-dom'
import ReactPlayer from 'react-player'
 
// component
import Logo from './Logo' 
import { useEffect, useState } from 'react'
import { postState } from './Playing'

// images
import bgHomered from '../Images/BgHomeRed.png'
import bgHomegreen from '../Images/BgHomeGreen.png'

const MainMenu = () => { 
    let nav = useNavigate()

    const [play, setplaying] = useState<boolean>(true)
   
    useEffect(()=>{
        postState("MAIN_MENU")
     },[])

    
    const BGover = () => {
        const bg = document.querySelector('body')
            if (bg) bg.style.cssText += `background-image: url(${bgHomered});`
    }

    const BGleave = () => {
        const bg = document.querySelector('body')
            if (bg) bg.style.cssText += `background-image: url(${bgHomegreen});`
    }

    return (
        <div>
            <ReactPlayer style={{display: "none"}} loop={play} playing={play} onReady={() => {setplaying(true)}}
             url='https://www.youtube.com/watch?v=ZlZg2zJHK78&ab_channel=Dest'/>
            <Logo/> 
            <div className={styles.container}>
            <a onMouseOverCapture={() => {BGover()}} onMouseLeave={() => {BGleave()}} 
            onClick={() => nav('/setup')} className={styles.btn}> 
                    <span>Start</span>
                    <div className={styles.bthbefore}></div>
                </a>
            </div>
        </div>
    )
}

export default MainMenu
