// Hook
import styles from '../CSSstyle/manu.module.css'
import { useNavigate } from 'react-router-dom'
 
// component
import Logo from './Logo' 

const MainMenu = () => { 
    let nav = useNavigate()


    return (
        <div>
            <Logo/> 
            <div className={styles.container}>
                <a onClick={() => nav('/gameplay')} className={styles.btn}> 
                    <span>Start</span>
                    <div className={styles.bthbefore}></div>
                </a>
            </div>
        </div>
    )
}

export default MainMenu
