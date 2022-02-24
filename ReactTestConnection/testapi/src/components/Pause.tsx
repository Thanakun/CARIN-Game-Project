// Hook
import { useNavigate } from 'react-router-dom'

// css
import styles from '../CSSstyle/puase.module.css'

const Pause = () => {
let nav = useNavigate()

    return (
        <div>
            <a onClick={() =>nav('/gameplay')} className={styles.btn}> 
                <span>Shop</span>
                <div className={styles.bthbefore}></div>
            </a>
        </div>
    )
}

export default Pause