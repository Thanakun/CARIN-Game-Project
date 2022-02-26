// Hook
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
        if (bg) bg.style.cssText = `background: url(${BgPuase}) no-repeat center center fixed;width: 50%;`
    }, [])
let nav = useNavigate()


    return (
        <div>
        <div onClick={() => nav('/gameplay')} className={styles.btnBack}> 
            <span>Unpause</span>
        </div>
        <div onClick={() => nav('/')} className={styles.btnOut}> 
            <span>MainMenu</span>
        </div>
    </div>
    )
}

export default Pause