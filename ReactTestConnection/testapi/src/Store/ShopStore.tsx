import {Store} from 'pullstate'

type ShopStoreType = {
    shopStatus:boolean,
    shopLocate:{
        x:number,
        y:number,
        index:number[]
    }
}

export const ShopStore = new Store<ShopStoreType>({
    shopStatus:false,
    shopLocate:{
        x:0,
        y:0,
        index:[0,0]
    }
})

//custom hook
export function useShopStore():ShopStoreType{  //use to pull data store element
    return ShopStore.useState(s=>s)
}