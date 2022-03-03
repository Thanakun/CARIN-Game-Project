// images 
import virus from '../Images/Blue Virus.png'

// css
import styles from '../CSSstyle/testblood.module.css'

//hook
import { useEffect, useState } from 'react'

// Store
import { Store } from 'pullstate'
import { MapStore } from '../Store/MapStore'

const testblood = () => {
    // variables
    const [currentBlood, setcurrentBlood] = useState<number>(40)
    const maxHp : number = 100
    const stat = MapStore.useState()

    // functions
    const blood = () => {
        const object = document.querySelector('.testblood_containerBlood__kQ0Oz')
        const blood = document.createElement('span')
        const bloodcurrent = document.createElement('span')
    
        if (object) {
        if (object.children[1] != null) 
            object.removeChild(object.children[1]);
        }
        blood.classList.add(`${styles.blood}`)
        blood.style.cssText = `position: absolute; width: ${stat.max_scale*0.8}px; height: ${stat.max_scale*0.05}px;`
        let Hp = currentBlood/maxHp*100 
        let collorHp = '' 
        if (Hp > 50) {
            collorHp = 'rgb(0, 255, 34)'
        }else if (Hp > 30) {
            collorHp = '#fbbf24'
        }else if (Hp > 15) {
            collorHp = '#f97316'
        }else {
            collorHp = '#ef4444'
        }
        bloodcurrent.style.cssText = `position: absolute; width: 100%; height: 100%; left: ${Hp-100}%; background-color: ${collorHp};`
        blood.appendChild(bloodcurrent)
        if (object) object.appendChild(blood)
    }

    // Hooks
    useEffect(() => {
        blood()
    },[currentBlood])

    const tag : JSX.Element = <a className={styles.containerBlood}></a> 

    return (
        <div>
            <div className={styles.containerBlood}><img src={virus} alt="" className={styles.virusHHHH}/></div>
            {tag}
            {/* <input type="text" id="fname" name="fname" onChange={(e) => {setcurrentBlood(Number(e.target.value)); console.log(currentBlood)}}/><br/><br/>
            <input type="submit" value="Submit"/> */}
            {/* <span className={styles.blood}></span> */}
        </div>
    )
}

export default testblood