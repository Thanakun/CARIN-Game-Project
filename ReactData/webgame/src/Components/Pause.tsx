// css
import styles from '../CSSstyle/puase.module.css'
type typePops = {
    funct : (e : string) => void
    path : string
}

const Pause = ({funct, path} : typePops) => {
    return (
        <div>
            <a onClick={() => funct(path)} className={styles.btn}> 
                <span>Shop</span>
                <div className={styles.bthbefore}></div>
            </a>
        </div>
    )
}

export default Pause