// Store
import { MapStore } from '../Store/MapStore'

// Store method
import { updatestatusShop } from '../Store/MapStore'

// css
import { useNavigate } from 'react-router-dom'
import styles from '../CSSstyle/dropbarMenu.module.css'

// images 
import LabelMenu from '../Images/newMenu.png'
import LabelPause from '../Images/submenupause.png'
import LabelCharge from '../Images/submenucharge.png'
import LabelSetting from '../Images/submenusetting.png'
import LabelOut from '../Images/submenuout.png'
import stick from '../Images/stick.png'
import { useEffect } from 'react'

const DropbarMenu = () => {
    // variables
    useEffect(() => {
        const pause = document.getElementById('Pause')
        if (pause) {
            pause.style.cssText = 'display: none;'
        }
        const cash = document.getElementById('Cash')
        if (cash) {
            cash.style.cssText = 'display: none;'
        }
        const setting = document.getElementById('Setting')
        if (setting) {
            setting.style.cssText = 'display: none;'
        }
        const out = document.getElementById('Out')
        if (out) {
            out.style.cssText = 'display: none;'
        }
        const stick = document.getElementById('Stick')
        if (stick) {
            stick.style.cssText = 'display: none;'
        }   
      },[])
    let nav = useNavigate()
    const state = MapStore.useState()

    // functions
    const goto = (path : string) => {
        nav(path)
    }

    const closeshop = (arr :number[]) => {
        const td = document.querySelectorAll('td')[arr[0]*state.x+arr[1]]
        td.style.cssText = "td{opacity: 1;transform: scale(1);transition: 0.1s ease-in-out;margin: 0;padding: 0;} td:hover{opacity: 0.5;transform: scale(1.2);}"
    
    }

    const setstyle = () => {
        const pause = document.getElementById('Pause')
        if (pause) {
            pause.style.cssText = ''
        }
        const cash = document.getElementById('Cash')
        if (cash) {
            cash.style.cssText = ''
        }
        const setting = document.getElementById('Setting')
        if (setting) {
            setting.style.cssText = ''
        }
        const out = document.getElementById('Out')
        if (out) {
            out.style.cssText = ''
        }
        const stick = document.getElementById('Stick')
        if (stick) {
            stick.style.cssText = ''
        }
    }

    return (
        <div className={styles.container}>
            <div className={styles.menu}><a onClick={() => {MapStore.update(
                s => {
                    s.menustatus = !s.menustatus
                })
                    setstyle()
                }}>
                    <img src={LabelMenu} alt="" />
                </a>
            </div>
            <div id='Pause' className={state.menustatus? styles.pause : styles.pausesleep} onClick={() => {goto('/pause');MapStore.update(s => {s.menustatus = !s.menustatus});closeshop(state.shoplocate.index); updatestatusShop([0,0], [0,0]);}}><a><img src={LabelPause} alt="" /></a></div>
            <div id='Cash' className={state.menustatus? styles.cash : styles.cashsleep}><a onClick={() => {}}><img src={LabelCharge} alt="" /></a></div>
            <div id='Setting'className={state.menustatus? styles.setting : styles.settingsleep}><a onClick={() => {}}><img src={LabelSetting} alt="" /></a></div>
            <div id='Out' className={state.menustatus? styles.outgame : styles.outgamesleep}><a onClick={() => {goto('/');MapStore.update(s => {s.menustatus = !s.menustatus});closeshop(state.shoplocate.index); updatestatusShop([0,0], [0,0]);}}><img src={LabelOut} alt="" /></a></div>
            <div id='Stick' className={state.menustatus? styles.stick : styles.sticksleep}><img src={stick} alt="" /></div>
        </div>
    )
}

export default DropbarMenu