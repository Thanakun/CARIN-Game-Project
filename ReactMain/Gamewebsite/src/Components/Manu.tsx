// Hook
import styles from '../CSSstyle/manu.module.css'
import { useNavigate } from 'react-router-dom'
 
// component
import Logo from './Logo' 

const manu = () => { 
    let nav = useNavigate()

    const addpath = (path : string) =>{
          nav(path)
    }

    return (
        <div>
            <Logo/> 
            <div className={styles.container}>
                <a onClick={() => addpath('/gameplay')} className={styles.btn}> 
                    <span>Start</span>
                    <div className={styles.bthbefore}></div>
                </a>
            </div>
        </div>
    )
}

export default manu
