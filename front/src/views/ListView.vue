<template>
  <div class="container mt-3">
    <h1 class="display-1 text-center">온도 목록</h1>
    <div>
      <router-link to="/list/create" class="btn btn-primary">온도 생성</router-link>
    </div>
    <input
      class="search-input"
      type="text"
      placeholder="날짜(yyyymmdd)를 입력하세요"
      @input="searchGroup($event)"
    />
    <table class="table table-hover mt-3">
      <thead class="table-dark">
        <tr>
          <th>날짜(yyyymmdd)</th>
          <th>09시 온도</th>
          <th>13시 온도</th>
          <th>17시 온도</th>
          <th>점검자 이름</th>
        </tr>
      </thead>
      <tbody>
        <tr class="cursor-pointer" v-for="row in filteredResult" v-bind:key="row.id" v-on:click="href(row)">
          <td>{{ dateTime(row.regDate) }}</td>
          <td>{{ row.temperature09 }}</td>
          <td>{{ row.temperature13 }}</td>
          <td>{{ row.temperature17 }}</td>
          <td>{{ row.name }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios';
import dayjs from 'dayjs';
import store from '@/store';

export default {
  name: 'ListView',
  data() {
    return {
      result: [],
      searchQuery: ''
    }
  },
  created() {
    this.getData();
  },
  computed: {
    filteredResult() {
      if (!this.searchQuery) {
        return this.result;
      } else {
        const searchDate = this.searchQuery.replace(/[^0-9]/g, ''); // 숫자만 추출
        return this.result.filter(row => row.regDate.includes(searchDate));
      }
    }
  },
  methods: {
    getData() {
      axios
        .post('http://localhost:8084/api/findAll')
        .then((response) => {
          this.result = response.data.result;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    href(row) {
      this.$store.commit('setUser', row);
      this.$router.push({ name: 'SaveView' });
    },
    dateTime(value) {
      return dayjs(value).format('YYYY-MM-DD HH:mm:ss');
    },
    searchGroup(event) {
      this.searchQuery = event.target.value;
    }
  }
}
</script>

<style scoped>
.search-input {
  display: block;
  padding: 4px 8px;
  margin: 10px auto;
  width: 320px;
  font-size: 16px;
  outline: none;
}
</style>