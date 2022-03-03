
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
import { DataStore, useDataStore } from '../Store/DataStore'

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
   const dataStore = useDataStore()

    // functions
    const goto = (path : string) => {
        nav(path)
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
            <div className={styles.menu}><a onClick={() => {DataStore.update(
                s => {
                    s.menuState = !s.menuState
                })
                    setstyle()
                }}>
                    <img src={LabelMenu} alt="" />
                </a>
            </div>
            <div id='Pause' className={dataStore.menuState? styles.pause 
                : styles.pausesleep} onClick={() => {
                        goto('/pause');
                        DataStore.update(s => {
                            s.menuState = !s.menuState
                        })
                        
                        }}><a><img src={LabelPause} alt="" /></a></div>
            <div id='Cash' className={dataStore.menuState? styles.cash
                 : styles.cashsleep}><a onClick={() => {}}><img src={LabelCharge} alt="" /></a></div>
            <div id='Setting'className={dataStore.menuState? styles.setting 
                : styles.settingsleep}><a onClick={() => {}}><img src={LabelSetting} alt="" /></a></div>
            <div id='Out' className={dataStore.menuState? styles.outgame
                 : styles.outgamesleep}><a onClick={() => 
                 {
                     goto('/');
                 DataStore.update(s => {
                     s.menuState = !s.menuState
                   })
                 
                 }}><img src={LabelOut} alt="" /></a></div>
            <div id='Stick' className={dataStore.menuState? styles.stick 
                : styles.sticksleep}><img src={stick} alt="" /></div>
        </div>
    )
}

export default DropbarMenu