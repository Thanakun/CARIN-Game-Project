// Hooks
import { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'

// css
import styles from '../CSSstyle/setup.module.css'

const Setup = () => {
    // Hook
    useEffect(() => {
        effectBtn()
    },[])

    // variables
    const nav = useNavigate()

    const cardmove = () => {
        const container = document.getElementById('containerSetup')
            console.log("container : ", container)
        if (container) {
            container.addEventListener('mousemove', (e : any) => {
                // console.log('W : ', e.pageX - container.offsetLeft)
                // console.log('H : ', e.pageY - container.offsetTop)
                let xAxis = ((e.pageX - container.offsetLeft) - 220) / 18
                let yAxis = ((e.pageY - container.offsetTop) - 320) / 18
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

    const BGsetup= () => {
        const bg = document.querySelector('body')
            if (bg) bg.style.cssText += `background: #ccc; transition: all 0.8s; background-size: cover;`
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
    
    const addpath = (path : string) =>{
        nav(path)
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
    return (
        <>
            <a id='back' onClick={() => addpath('/')} className={styles.btnBack}>Back</a>
            <div id='containerSetup' className={styles.containerAll}>
            <div className={styles.container}>
                <form>
                    <textarea onClick={() => {showBtn()}} onBlur={() => {closeBtn()}} className={styles.textarea}  name="" id="" placeholder="Put your genetic..."></textarea>
                </form>
                <div className={styles.containerBtn}>
                    <button onClick={() => {reset()}} className={styles.btnRe}>RESET</button>
                    <button className={styles.btnDe}>DEFAULT</button>
                    <button className={styles.btnSub}>SUBMITE</button>
                </div>
            </div>
            </div>
        </>
    )
}

export default Setup