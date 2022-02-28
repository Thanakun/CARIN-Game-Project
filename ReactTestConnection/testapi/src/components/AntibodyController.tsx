
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



const AntibodyController = () => {
    const dataStore = useDataStore()
    const antibodyStore = useAntibodyStore()
    const controllerStore = useAntibodyControllerStore()

    const closecontroller = (arr :number[]) => {
        const td = document.querySelectorAll('td')[arr[0]*dataStore.max_x+arr[1]]
        td.style.cssText = "opacity: 1;transform: scale(1);"
    }

    const move = (direction:string)=>{
        AntibodyStore.update(s=>{
            s.genetic = "move "+direction
        }
        )

        const req:AntibodyStoreType = {
            targetId : antibodyStore.targetId,
            type:antibodyStore.type,
            location:antibodyStore.location,
            genetic: "move "+direction
        }
        postAntibody(req)
        closecontroller(controllerStore.locate.index)
        updateAntibodyController([0,0], [0,0])
    }

    const notmove = ()=>{
        AntibodyStore.update(s=>{
            s.genetic = "virusLoc = virus\n" +
            "if (virusLoc / 10 - 1)\n" +
            "then \n" +
            "  { }"
            + "else\n"+
           " if (virusLoc)\n" +
            "then \n" +
            "  if (virusLoc % 10 - 7) then shoot upleft\n" +
            "  else if (virusLoc % 10 - 6) then shoot left\n" +
            "  else if (virusLoc % 10 - 5) then shoot downleft\n" +
            "  else if (virusLoc % 10 - 4) then shoot down\n" +
            "  else if (virusLoc % 10 - 3) then shoot downright\n" +
            "  else if (virusLoc % 10 - 2) then shoot right\n" +
            "  else if (virusLoc % 10 - 1) then shoot upright\n" +
            "  else shoot up\n"
        }
        )
        const req:AntibodyStoreType = {
            targetId : antibodyStore.targetId,
            type:antibodyStore.type,
            location:antibodyStore.location,
            genetic: "virusLoc = virus\n" +
            "if (virusLoc / 10 - 1)\n" +
            "then \n" +
            "  { }"
            + "else\n"+
           " if (virusLoc)\n" +
            "then \n" +
            "  if (virusLoc % 10 - 7) then shoot upleft\n" +
            "  else if (virusLoc % 10 - 6) then shoot left\n" +
            "  else if (virusLoc % 10 - 5) then shoot downleft\n" +
            "  else if (virusLoc % 10 - 4) then shoot down\n" +
            "  else if (virusLoc % 10 - 3) then shoot downright\n" +
            "  else if (virusLoc % 10 - 2) then shoot right\n" +
            "  else if (virusLoc % 10 - 1) then shoot upright\n" +
            "  else shoot up\n"
        }
        postAntibody(req)
        closecontroller(controllerStore.locate.index)
        updateAntibodyController([0,0], [0,0])
    }

    const cardmove = () => {
        // const card = document.getElementById('myShop')
        const container = document.getElementById('container')
        // console.log('card : ', card, " container : ", container)
        if (container) {
            container.addEventListener('mousemove', (e : any) => {
                let xAxis = (window.innerWidth  / 2 - e.pageX) / 9
                let yAxis = (window.innerHeight / 2 - e.pageY) / 9
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
          style={{top : `${controllerStore.locate.y-125}px`, left: `${controllerStore.locate.x-125}px`}}>
            <div id='card' className={styles.exit1}>
                <a onClick={() => {
                    notmove()
                 }}><span><IoCloseSharp/></span></a>
            </div>
            <div className={styles.item1}>
                <div className={styles.TL} onClick={()=>move("downleft")}><IoIosArrowDown className={styles.iconAll}/></div>
                <div className={styles.T} onClick={()=>move("left")}><IoIosArrowDown className={styles.iconAll}/></div>
                <div className={styles.TR} onClick={()=>move("upleft")}><IoIosArrowDown className={styles.iconAll}/></div>
                <div className={styles.R} onClick={()=>move("up")}><IoIosArrowDown className={styles.iconAll}/></div>
                <div className={styles.BR} onClick={()=>move("upright")}><IoIosArrowDown className={styles.iconAll}/></div>
                <div className={styles.B} onClick={()=>move("right")}><IoIosArrowDown className={styles.iconAll}/></div>
                <div className={styles.BL}onClick={()=>move("downright")}><IoIosArrowDown className={styles.iconAll}/></div>
                <div className={styles.L} onClick={()=>move("down")}><IoIosArrowDown className={styles.iconAll}/></div>
            </div>
        </div>
    )
}

export default AntibodyController