import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import { AntibodyStoreType, postAntibody } from "../Store/AntibodyStore"
import { postState } from './Playing'
import ReactPlayer from "react-player";

// css
import styles from '../CSSstyle/setup.module.css'
//pic
import Bgsetup from '../Images/Bgsetup.png'

const Setup = ()=>{
    const [genetic,setGenetic] = useState<string>("")
    const nav = useNavigate()

    useEffect(()=>{
        postState("SETUP")
    },[])



    const BGsetup= () => {
        const bg = document.querySelector('body')
            if (bg) bg.style.cssText += `background: url(${Bgsetup}); transition: all 0.8s; background-size: cover;`
    }

    useEffect(() => {
        BGsetup()
        cardmove()
        effectBtn()
    }, [])

    const showBtn = () => {
        const btns = document.querySelectorAll('button')
        // console.log(btns)
        if (btns) {
            for (let i=0;i<btns.length;i++) {
                btns[i].style.cssText += 'z-index: 0; opacity: 1; transition: all 0.5s ease-in-out;'
            }
        } 
    }

    const closeBtn = () => {
        const btns = document.querySelectorAll('button')
        // console.log(btns)
        if (btns) {
            for (let i=0;i<btns.length;i++) {
                btns[i].style.cssText += 'z-index: -1; opacity: 0; transition: all 0.5s ease-in-out;'
            }
        } 
    }

    const effectBtn = () => {
        const btnback = document.getElementById('back')

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
        
        if (btnback) 
            btnback.addEventListener('mouseover', (e) => {WaveEffect(e, btnback)})
    }
    
    const reset = () => {
        const textArea = document.querySelectorAll('textarea')
        if (textArea) textArea[0].value = ''
    }

    const cardmove = () => {
        const container = document.getElementById('containerSetup')
            console.log("container : ", container)
        if (container) {
            container.addEventListener('mousemove', (e : any) => {
                // console.log('W : ', e.pageX - container.offsetLeft)
                // console.log('H : ', e.pageY - container.offsetTop)
                let xAxis = ((e.pageX - container.offsetLeft) - 220) / 20
                let yAxis = ((e.pageY - container.offsetTop) - 320) / 20
                // container.style.cssText += `transform-origin: center center;`
                container.style.cssText += 'transition: none;'
                container.style.cssText += `transform: rotateY(${xAxis}deg) rotateX(${yAxis}deg);`
            })
            container.addEventListener('mouseenter', (e : any) => {
                container.style.cssText += 'transition: all 1s ease;'
            })
            container.addEventListener('mouseleave', (e : any) => {
                container.style.cssText += 'transition: all 0.5s ease;'
                container.style.cssText += `transform: rotateY(0deg) rotateX(0deg);`
            })
        }
    }

    const postGenetic = (genetic:string) =>{
        const req:AntibodyStoreType={
            targetId:"",
            type:0,
            location:[0,0],
            cmd:"setup",
            genetic:genetic
        }
        postAntibody(req)
        nav("/gameplay")
    }

 return (
        <>
        <ReactPlayer style={{display: "none"}} loop={true} playing={true}url="https://www.youtube.com/watch?v=zcIDJd-ncHI"/>
            <a id='back' onClick={() => nav('/')} className={styles.btnBack}>Back</a>
            <div id='containerSetup' className={styles.containerAll}>
            <div className={styles.container}>
                <form>
                    <textarea onClick={() => {showBtn()}} onBlur={() => {closeBtn()}} className={styles.textarea} onChange={(e)=>{setGenetic(e.target.value)}}
                     name="" id="" placeholder="Put your genetic..."></textarea>
                </form>
                <div className={styles.containerBtn}>
                    <button onClick={() => {reset()}} className={styles.btnRe}>RESET</button>
                    <button className={styles.btnDe} onClick={e=>{postGenetic("default")}}>DEFAULT</button>
                    <button className={styles.btnSub} onClick={e=>postGenetic(genetic)}>SUBMIT</button>
                </div>
            </div>
            </div>
        </>
    )
   
}

export default Setup