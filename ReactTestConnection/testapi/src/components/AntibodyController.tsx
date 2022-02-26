
import styles from '../CSSstyle/shop.module.css'
import { AntibodyControllerStore, useAntibodyControllerStore } from '../Store/AntibodyControllerStore'
import { AntibodyStore, AntibodyStoreType, postAntibody, useAntibodyStore } from '../Store/AntibodyStore'
import { useDataStore } from '../Store/DataStore'


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
 
            

    return(
        <div id='AntibodyController' className={controllerStore.status? styles.isOpen : styles.isnotOpen} style={{top : `${controllerStore.locate.y}px`, left: `${controllerStore.locate.x}px`}}>
            <div className={styles.exit}>
                <a onClick={() => {
                    closecontroller(controllerStore.locate.index); 
                    updateAntibodyController([0,0], [0,0]);}}>
                        <span>X</span></a>
            </div>
            <div className={styles.item}>
                <div className={styles.type1}>
                    <a className={styles.type1price} onClick={()=>{move("up")}}>up</a>
                </div>
                <div>
                    <a className={styles.type2price} onClick={() => {move("down")}}>down</a>
                </div>
                <div className={styles.type2}>
                    <a className={styles.type2price} onClick={() => {move("left")}}>left</a>
                </div>
                <div className={styles.type2}>
                    <a className={styles.type2price} onClick={() => {move("right")}}>right</a>
                </div>
                <div className={styles.type2}>
                    <a className={styles.type2price} onClick={() => {move("upleft")}}>upleft</a>
                </div>
                <div className={styles.type2}>
                    <a className={styles.type2price} onClick={() => {move("upright")}}>upright</a>
                </div>
                <div className={styles.type2}>
                    <a className={styles.type2price} onClick={() => {move("downleft")}}>downleft</a>
                </div>
                <div className={styles.type2}>
                    <a className={styles.type2price} onClick={() => {move("downright")}}>downright</a>
                </div>
                <div className={styles.type2}>
                    <a className={styles.type2price} onClick={() => {notmove()}}>not move</a>
                </div>

            </div>
        </div>
    )
}

export default AntibodyController