// css
import styles from '../CSSstyle/nyancat.module.css'

// gif
import nyanCat from '../Images/nyan cat.gif'

const NyanCat = () => {
    return (
        <div className={styles.containerCat}>
            <img src={nyanCat} alt="" />
        </div>
    )
}

export default NyanCat