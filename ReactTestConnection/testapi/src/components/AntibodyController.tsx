
import { useEffect } from 'react'
import styles from '../CSSstyle/shop.module.css'
import { AntibodyControllerStore, useAntibodyControllerStore } from '../Store/AntibodyControllerStore'
import { AntibodyStore, AntibodyStoreType, postAntibody, useAntibodyStore } from '../Store/AntibodyStore'
import { useDataStore } from '../Store/DataStore'
import { IoIosArrowDown } from 'react-icons/io';
import { IoCloseSharp } from 'react-icons/io5';


export   const updateAntibodyController = (mouseindex : number[], index : number[]) => {
    AntibodyControllerStore.update(
        s => {
            s.status = !s.status
            s.locate.x = mouseindex[0]
            s.locate.y = mouseindex[1]
            s.locate.index[0] = index[0]
            s.locate.index[1] = index[1]
        }
    )
}


export const closecontroller = (arr :number[],max_x:number) => {
    const td = document.querySelectorAll('td')[arr[0]*max_x+arr[1]]
    td.style.cssText = "opacity: 1;transform: scale(1);"
}


const AntibodyController = () => {
    const dataStore = useDataStore()
    const controllerStore = useAntibodyControllerStore()



    const notmove = ()=>{
        closecontroller(controllerStore.locate.index,dataStore.max_x)
        updateAntibodyController([0,0], [0,0])
    }

    const cardmove = () => {
        // const card = document.getElementById('myShop')
        const container = document.getElementById('container')
        // console.log('card : ', card, " container : ", container)
        if (container) {
            container.addEventListener('mousemove', (e : any) => {
                let xAxis = ((e.pageX - container.offsetLeft) - 124) / 5
                let yAxis = ((e.pageY - container.offsetTop) - 124) / 5
                container.style.cssText += `transform: rotateY(${xAxis}deg) rotateX(${yAxis}deg);`
                // console.log(13212) 
            })
            container.addEventListener('mouseenter', (e : any) => {
                container.style.cssText += 'transition: none'
            })
            container.addEventListener('mouseleave', (e : any) => {
                container.style.cssText += 'transition: all 0.5s ease'
                container.style.cssText += `transform: rotateY(0deg) rotateX(0deg);`
            })
        }
    }

    useEffect(()=>{
        AntibodyControllerStore.update(s=>{
            s.status = false
        })
    },[dataStore.gameState])
    useEffect(() => {
        cardmove()
    }, [])
 
            

    return(
          <div id='container' className={controllerStore.status? styles.isOpen1 : styles.isnotOpen} 
          style={{top : `${controllerStore.locate.y-25}px`, left: `${controllerStore.locate.x-25}px`}}>
            <div id='card' className={styles.exit1}>
                <a onClick={() => {
                    notmove()
                 }}><span><IoCloseSharp/></span></a>
            </div>
            {/* <div className={styles.item1}>
                <div className={styles.TL} onClick={()=>move("upleft")}><IoIosArrowDown className={styles.iconAll}/></div>
                <div className={styles.T} onClick={()=>move("up")}><IoIosArrowDown className={styles.iconAll}/></div>
                <div className={styles.TR} onClick={()=>move("upright")}><IoIosArrowDown className={styles.iconAll}/></div>
                <div className={styles.R} onClick={()=>move("right")}><IoIosArrowDown className={styles.iconAll}/></div>
                <div className={styles.BR} onClick={()=>move("downright")}><IoIosArrowDown className={styles.iconAll}/></div>
                <div className={styles.B} onClick={()=>move("down")}><IoIosArrowDown className={styles.iconAll}/></div>
                <div className={styles.BL}onClick={()=>move("downleft")}><IoIosArrowDown className={styles.iconAll}/></div>
                <div className={styles.L} onClick={()=>move("left")}><IoIosArrowDown className={styles.iconAll}/></div>
            </div> */}
        </div>
    )
}

export default AntibodyController