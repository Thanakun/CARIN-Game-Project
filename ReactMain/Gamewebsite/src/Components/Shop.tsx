import { useState } from 'react'
import styles from '../CSSstyle/shop.module.css'

// Store
import { MapStore } from '../Store/MapStore'

// Store method
import { updatestatusShop } from '../Store/MapStore'

const Shop = () => {
    const state = MapStore.useState()

    const closeshop = (arr :number[]) => {
        const td = document.querySelectorAll('td')[arr[0]*state.x+arr[1]]
        td.style.cssText = "td{opacity: 1;transform: scale(1);transition: 0.1s ease-in-out;margin: 0;padding: 0;} td:hover{opacity: 0.5;transform: scale(1.2);}"
    
    }

    return(
        <div id='myShop' className={state.shopstatus? styles.isOpen : styles.isnotOpen} style={{top : `${state.shoplocate.y}px`, left: `${state.shoplocate.x}px`}}>
            <div className={styles.exit}>
                <a onClick={() => {closeshop(state.shoplocate.index); updatestatusShop([0,0], [0,0]);}}><span>X</span></a>
            </div>
            <div className={styles.item}>
                <div className={styles.type1}>
                    <a className={styles.type1name}>Type1</a>
                    <a className={styles.type1price}>100</a>
                </div>
                <div className={styles.type2}>
                    <a className={styles.type2name} onClick={() => {}}>Type2</a>
                    <a className={styles.type2price} onClick={() => {}}>200</a>
                </div>
            </div>
        </div>
    )
}

export default Shop