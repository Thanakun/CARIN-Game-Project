// Hook
import styles from '../CSSstyle/manu.module.css'
import { useNavigate } from 'react-router-dom'
import { useEffect, useState } from 'react'
import ReactPlayer from 'react-player'
 
// component
import Logo from './Logo' 

// images
import bgHomered from '../Images/bghomered.png'
import bgHomegreen from '../Images/bghome.png'

const menu = () => { 
    let nav = useNavigate()

    const addpath = (path : string) =>{
          nav(path)
    }

    // Hook
    const BGover = () => {
        const bg = document.querySelector('body')
            if (bg) bg.style.cssText += `background-image: url(${bgHomered}); transition: all 0.8s; background-size: cover;`
    }

    const BGleave = () => {
        const bg = document.querySelector('body')
            if (bg) bg.style.cssText += `background-image: url(${bgHomegreen}); transition: all 0.8s; background-size: cover;`
    }

    const runsound = () => {

    }

    return (
        <div>
            <ReactPlayer style={{display: "none"}} loop={true} playing={true} url='https://www.youtube.com/watch?v=ZlZg2zJHK78&ab_channel=Dest'/>
            <Logo/>
            <div className={styles.container}>
                <a onMouseOverCapture={() => {BGover()}} onMouseLeave={() => {BGleave()}} onClick={() => {runsound(); addpath('/gameplay')}} className={styles.btn}> 
                    <span>Start</span>
                    <div className={styles.bthbefore}></div>
                </a>
            </div>
        </div>
    )
}

export default menu
