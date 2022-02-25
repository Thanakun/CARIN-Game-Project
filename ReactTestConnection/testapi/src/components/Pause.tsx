// Hook
import { useNavigate } from 'react-router-dom'

// css
import styles from '../CSSstyle/puase.module.css'


const Pause = () => {
let nav = useNavigate()

const unpauseClick= ()=>{
    nav('/gameplay')
}

    return (
        <div>
            <a onClick={()=>unpauseClick()} className={styles.btn}> 
                <span>Unpause</span>
                <div className={styles.bthbefore}></div>
            </a>
        </div>
    )
}

export default Pause