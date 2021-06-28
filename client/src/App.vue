<template>
<div id="app">
    <div class="panel panel-default">
      <div class="panel-heading"></div>
      <div class="row">
        <div class="search-wrapper panel-heading col-sm-12">
          <input class="form-control" type="text" v-model="searchQuery" placeholder="Search" />
        </div>
      </div>
      <div class="table-responsive">
        <table v-if="stores.length" class="table" bordered>
          <thead>
            <tr>
              <th>Id</th>
              <th>Code</th>
              <th>Description</th>
              <th>Name</th>
              <th>OpeningDate</th>
              <th>StoreType</th>
              <th>Season</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="store in resultQuery" :key="store.name">
              <td>{{store.id}}</td>
              <td>{{store.code}}</td>
              <td>{{store.description}}</td>
              <td contenteditable="true"><input v-model="store.name"></td>
              <td>{{store.openingDate}}</td>
              <td>{{store.storeType}}</td>
              <td>{{store.season}}</td>
              <td>
              <span>
                <button type="button" class="btn btn-danger btn-rounded btn-sm my-0"  v-on:click="saveStore(store)">
                  Save
                </button></span>
            </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <button type="button" class="btn btn-info action_btn" v-on:click="downloadCSVData">
      Download
</button>
  </div>
</template>

<script>


import axios from 'axios'

export default {
  name: "App",
  data() {
    return {
        searchQuery: null,
        stores:[]
    };
  },
  beforeCreate(){

    var self = this
    const storesUrl = 'http://localhost:8090/stores'
    const saveStoreDataInDatabaseUrl = "http://localhost:8090/saveStoreDataInDatabase"
    const saveStoreAndSeasonDataInDatabaseurl = "http://localhost:8090/saveStoreAndSeasonDataInDatabase"

      axios.get(saveStoreDataInDatabaseUrl, {
      dataType: 'json',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      mode: 'no-cors',
      credentials: 'include'
    })
    .then(function (response) {
      console.log(JSON.stringify(response.data))
    })
    .catch(function (error) {
      console.log(error)
    })

      axios.get(saveStoreAndSeasonDataInDatabaseurl, {
      dataType: 'json',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      mode: 'no-cors',
      credentials: 'include'
    })
    .then(function (response) {
      console.log(JSON.stringify(response.data))
    })
    .catch(function (error) {
      console.log(error)
    })

    axios.get(storesUrl, {
      dataType: 'json',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      mode: 'no-cors',
      credentials: 'include'
    })
    .then(function (response) {
      console.log(JSON.stringify(response.data))
      self.stores = response.data
    })
    .catch(function (error) {
      console.log(error)
    })

  },
  computed: {
    resultQuery(){
      if(this.searchQuery){
      return this.stores.filter((store)=>{
        
        console.log(JSON.stringify(store))
        return this.searchQuery.toLowerCase().split(' ').every(v => store.name?.toLowerCase().includes(v))
      })
      }else{
        return this.stores;
      }
    }
  },
  methods: {
    saveStore(store){
      const saveUpdatedStoreUrl = 'http://localhost:8090/store/'
      console.log(JSON.stringify(store))
      axios.put(saveUpdatedStoreUrl + store.id, store,{
      dataType: 'json',
      headers: {
        'Content-Type': 'application/json'
      },
      mode: 'no-cors',
      credentials: 'include'
    })
    .then(function (response) {
      console.log(JSON.stringify(response.data))
    })
    .catch(function (error) {
      console.log(error)
    })
    },
    downloadCSVData() {
      let csv = 'id,code,description,name,openingDate,storeType,season\n';

      let arr = [];
      this.stores.forEach(store => {
        arr.push(Object.values(store))
      })
    
      arr.forEach((row) => {
              csv += row.join(',');
              csv += "\n";
      });
   
      const anchor = document.createElement('a');
      anchor.href = 'data:text/csv;charset=utf-8,' + encodeURIComponent(csv);
      anchor.target = '_blank';
      anchor.download = 'stores.csv';
      anchor.click();
  }
  }
}
</script>

<style>
@import 'https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css';
</style>