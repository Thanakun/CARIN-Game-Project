// Hook
import { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import ReactPlayer from 'react-player'

// css
import styles from '../CSSstyle/pause.module.css'
// images
import BgPuase from '../Images/Bgstart.png'
import superAntibody from '../Images/testAnti.gif'
import cloud1 from '../Images/cloud1.png'
import cloud2 from '../Images/cloud2.png'
import cloud3 from '../Images/cloud3.png'
import RedVirus from '../Images/Red Virus.png'
import GreenVirus from '../Images/Green Virus.png'
import BlueVirus from '../Images/Blue Virus.png'
import RBC1 from '../Images/RBC_1.png'
import { DataStore } from '../Store/DataStore'

const Pause = () => {
   
let nav = useNavigate()
// functions
const createBubble = () => {
    const bowl = document.getElementById('circle')
    const createElement = document.createElement('span')
    let size = Math.random() * 3

    createElement.style.width += 20 + size + 'px';
    createElement.style.height += 20 + size + 'px'

    createElement.style.left += `${Math.random() * 500}px`;
    bowl?.appendChild(createElement)

    setTimeout(() => {
        createElement.remove()
    }, 4000)
}

const createStar = () => {
    let count = 700;
    let scene = document.getElementById('scenes')
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
    const btnback = document.getElementById('back')
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
    if (btnback) 
        btnback.addEventListener('mouseover', (e) => {WaveEffect(e, btnback)})
}


// Hook
useEffect(() => {
    DataStore.update(s=>{s.gameState="PAUSE"})
    setInterval(createBubble,100)
    createStar()
    effectBtn()
}, [])


    return (
        <div>
              <ReactPlayer style={{display: "none"}} loop={true} playing={true}
               url='https://www.youtube.com/watch?v=jCQ_5Gj6jlg&ab_channel=Misaki'/>
            {/* <img className={styles.bg} src={BgPuase} alt="" /> */}
            <a id='back' onClick={() => nav('/gameplay')} className={styles.btnBack}>Back</a>
            <a id='out' onClick={() => nav('/')} className={styles.btnOut}>Out</a>
            <div id='circle' className={styles.bowl}>
                <div className={styles.clouds}>
                    <img src={cloud1} alt="" />
                    <img src={cloud2} alt="" />
                    <img src={cloud3} alt="" />
                </div>
                <div className={styles.water}></div>
                <div className={styles.superAntibody}>
                    <img src={superAntibody} alt=""/>
                </div>
                <div className={styles.moon}></div>
                <div className={styles.virus1}>
                    <img src={RedVirus} alt="" />
                    <img src={RedVirus} alt="" />
                    <img src={GreenVirus} alt="" />
                    <img src={BlueVirus} alt="" />
                </div>
                <div className={styles.virus2}>
                    <img src={RedVirus} alt="" />
                    <img src={BlueVirus} alt="" />
                    <img src={BlueVirus} alt="" />
                    <img src={GreenVirus} alt="" />
                    <img src={RedVirus} alt="" />
                </div>
                <div className={styles.RBC}>
                    <img src={RBC1} alt="" />
                    <img src={RBC1} alt="" />
                    <img src={RBC1} alt="" />
                    <img src={RBC1} alt="" />
                    <img src={RBC1} alt="" />
                    <img src={RBC1} alt="" />
                </div>
            </div>
            <div id='scenes'className={styles.scene}></div>
        </div>
    )
}

export default Pause