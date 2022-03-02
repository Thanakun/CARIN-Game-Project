// Hook
import styles from '../CSSstyle/manu.module.css'
import { useNavigate } from 'react-router-dom'
import { useEffect } from 'react'
 
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
                <a onMouseOverCapture={() => {BGover()}} onMouseLeave={() => {BGleave()}} onClick={() => addpath('/test')} className={styles.btn}> 
                    <span>Start</span>
                    <div className={styles.bthbefore}></div>
                </a>
            </div>
        </div>
    )
}

export default menu
