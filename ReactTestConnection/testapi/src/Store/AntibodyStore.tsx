import axios from 'axios'
import {Store} from 'pullstate'

export type AntibodyStoreType = {
    targetId: string,
    type:number,
    location:number[],
    cmd:string,
    genetic:string
}

export const AntibodyStore = new Store<AntibodyStoreType>({
    targetId : "",
    type:0,
    location:[0,0],
    cmd:"",
    genetic:""
})

export function useAntibodyStore():AntibodyStoreType{
    return AntibodyStore.useState(s=>s)
}

export const postAntibody = async(req:AntibodyStoreType)=>{
    try{
        axios.post<AntibodyStoreType>('http://localhost:8080/game/input/antibody',req)
            .then(resp=>console.log(resp.data))
    }
    catch(err){
        console.log(err)
    }
}