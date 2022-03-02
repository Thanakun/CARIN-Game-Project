import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

// css
import styles from '../CSSstyle/finish.module.css'

const Win = () => {
    // Hook
    useEffect(() => {
        createStar()
        effectBtn()
    },[])

    // variables
    const nav = useNavigate()

    // functions
    const createStar = () => {
        let count = 700;
        let scene = document.getElementById('scenewin')
        let i = 0
        while(i < count) {
            let star = document.createElement('i')
            let x = Math.floor(Math.random() * window.innerWidth)
            let y = Math.floor(Math.random() * window.innerHeight)
            let duration = Math.random() * 10
            let size = Math.random() * 2
    
            star.style.left = x + 'px'
            star.style.top = y + 'px'
            star.style.width = 1 + size + 'px'
            star.style.height = 1 + size + 'px'
    
            star.style.animationDuration = 5 + duration + 's'
            star.style.animationDelay = duration + 's'
    
            scene?.appendChild(star)
            i++
        }
    }

    const addpath = (path : string) =>{
        nav(path)
    }

    const effectBtn = () => {
        const btnout = document.getElementById('out')

        const WaveEffect = (e : MouseEvent, btn : HTMLElement) => {
            let x = e.clientX - btn.offsetLeft
            let y = e.clientY - btn.offsetTop
            let ripples = document.createElement('span')
           
            ripples.style.left = x + 'px'
            ripples.style.top = y + 'px'
            btn.appendChild(ripples)

            setTimeout(() => {
                ripples.remove()
            }, 1000)
        }
        
        if (btnout)
            btnout.addEventListener('mouseover', (e) => {WaveEffect(e, btnout)})
    }

    return(
        <div>
            <a id='out' onClick={() => addpath('/')} className={styles.btnOut}>Out</a>
            <div id='scenewin' className={styles.sceneWin}><span>Victory</span></div>
        </div>
    )    
}

export default Win