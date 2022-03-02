
import { useEffect } from "react"
import { useNavigate } from "react-router-dom"
import {  useDataStore } from "../Store/DataStore"


// css
import styles from '../CSSstyle/finish.module.css'


const End = ()=>{
    const dataStore = useDataStore()
    const nav = useNavigate()

    // Hook
    useEffect(() => {
        createStar()
        effectBtn()
    },[])


    // functions
    const createStar = () => {
        let count = 700; 
        let scene = document.getElementById('scenelose')
        if(dataStore.gameState==="WIN"){
            scene = document.getElementById('scenewin')
        }
       
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


    const render = ()=>{
        if(dataStore.gameState==="WIN"){
            return (
                <div>
                <a id='out' onClick={() => nav('/')} className={styles.btnOut}>Out</a>
                <div id='scenewin' className={styles.sceneWin}><span>Victory</span></div>
            </div>
            )
        }
        else {
            return(
                <div>
                <a id='out' onClick={() => nav('/')} className={styles.btnOut}>Out</a>
                <div id='scenelose' className={styles.sceneLose}><span>Defeat</span></div>
            </div>
            )
        }
    }
   


    return(
           render()
     )
}

export default End