import styles from '../CSSstyle/manu.module.css'
import { useEffect } from 'react'
 
type manuPops = {
    funct : (e : string) => void
    path : string
}

const manu = ({funct, path} : manuPops) => { 
    return (
        <div className={styles.container}>
            <a onClick={() => funct(path)} className={styles.btn}> 
                <span>Start</span>
                <div className={styles.bthbefore}></div>
            </a>
        </div>
    )
}

export default manu
