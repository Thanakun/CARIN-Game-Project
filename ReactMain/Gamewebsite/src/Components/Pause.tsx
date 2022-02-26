// Hook
import { url } from 'inspector'
import { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'

// css
import styles from '../CSSstyle/puase.module.css'

// images
import BgPuase from '../Images/bg_pause.png'

const Pause = () => {
    // Hook
    useEffect(() => {
        const bg = document.querySelector('body')
        if (bg) bg.style.cssText = `background: url(${BgPuase}) no-repeat center center fixed;width: 120%;`
    }, [])

    // variables
    let nav = useNavigate()

    const addpath = (path : string) =>{
        nav(path)
    }
    return (
        <div>
            {/* <img className={styles.bg} src={BgPuase} alt="" /> */}
            <div onClick={() => addpath('/gameplay')} className={styles.btnBack}> 
                <span>Back</span>
            </div>
            <div onClick={() => addpath('/')} className={styles.btnOut}> 
                <span>Out</span>
            </div>
        </div>
    )
}

export default Pause