<template>
  <div class="home">
    <div class="album py-5 bg-body-tertiary">
      <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
          <div class="col" v-for="idx in 12" :key="idx">
            <div class="card shadow-sm">
              <div class="card-body">
                <p class="card-text">년월</p>
                <div class="d-flex justify-content-between align-items-center">
                  <div class="btn-group">
                    <router-link to="/list" class="btn btn-primary">수정</router-link>
                  </div>
                  <small class="text-body-secondary">{{ latestModifiedDate }}</small>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'HomeView',
  data() {
    return {
      latestModifiedDate: ''
    };
  },
  mounted() {
    this.latestModifiedDate = this.getFormattedCurrentDate();
  },
  methods:{
  getData() {
    axios
    .put('http://localhost:8084/api/save')
    .then((response) => {
      console.log(response)
    })
    .catch((error) => {
      console.log(error)
    })
  },
  getFormattedCurrentDate() {
      const currentDate = new Date();
      return currentDate.toLocaleString(); // 원하는 형식으로 날짜를 포맷팅할 수 있습니다.
    }
}
}
</script>
