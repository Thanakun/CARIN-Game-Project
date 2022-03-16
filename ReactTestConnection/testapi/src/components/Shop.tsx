
import { useEffect } from 'react'
import styles from '../CSSstyle/shop.module.css'
import { AntibodyStore, AntibodyStoreType, postAntibody, useAntibodyStore } from '../Store/AntibodyStore'
import { useDataStore } from '../Store/DataStore'
import { ShopStore, useShopStore } from '../Store/ShopStore'

// icons
import { IoIosArrowDown } from 'react-icons/io';
import { IoCloseSharp } from 'react-icons/io5';


export   const updatestatusShop = (mouseindex : number[], index : number[]) => {
    ShopStore.update(
        s => {
            s.shopStatus = !s.shopStatus
            s.shopLocate.x = mouseindex[0]
            s.shopLocate.y = mouseindex[1]
            s.shopLocate.index[0] = index[0]
            s.shopLocate.index[1] = index[1]
        }
    )
}



const Shop = () => {
    const shopStore = useShopStore()
    const dataStore = useDataStore()
    const antibodyStore = useAntibodyStore()

    const closeshop = (arr :number[]) => {
        const td = document.querySelectorAll('td')[arr[0]*dataStore.max_x+arr[1]]
        td.style.cssText = "td{opacity: 1;transform: scale(1);transition: 0.1s ease-in-out;margin: 0;padding: 0;} td:hover{opacity: 0.5;transform: scale(1.2);}"
    }

    const buyAntibody = (type:number)=>{
        AntibodyStore.update(s=>{
            s.type = type
        })
        const req:AntibodyStoreType = {
            targetId : antibodyStore.targetId,
            type: type,
            location:antibodyStore.location,
            cmd:"buy",
            genetic: ""
        }
        postAntibody(req)
        closeshop(shopStore.shopLocate.index)
        updatestatusShop([0,0], [0,0])
    }

    useEffect(()=>{
        ShopStore.update(s=>{
            s.shopStatus = false
        })
    },[dataStore.gameState])

   

    return(
    

        // อันเก่า
        <div  className={shopStore.shopStatus? styles.isOpen2 : styles.isnotOpen}
         style={{top : `${shopStore.shopLocate.y}px`, left: `${shopStore.shopLocate.x}px`}}>
        <div id='card' className={styles.exit2}>
            <a onClick={() => {closeshop(shopStore.shopLocate.index); updatestatusShop([0,0], [0,0]);}}>
                <span><IoCloseSharp/></span></a>
        </div>
        <div className={styles.item2}>
            <div className={styles.type1}>
                <a className={styles.type1name}>Type1</a>
                <a className={styles.type1price } onClick={() => {buyAntibody(1)}}>100</a>
            </div>
            <div className={styles.type2}>
                <a className={styles.type2name}>Type2</a>
                <a className={styles.type2price} onClick={() => {buyAntibody(2)}}>200</a>
            </div>
            <div className={styles.type3}>
                <a className={styles.type3name} >Type3</a>
                <a className={styles.type3price} onClick={() => {buyAntibody(3)}}>300</a>
            </div>
        </div>
        </div>
        
    )
}

export default Shop