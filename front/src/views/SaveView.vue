<template>
    <div class="container mt-3">
      <h1 class="display-1 text-center">온도 수정 및 삭제</h1>
      <form>
        <div class="mb-3 mt-3">
          <label for="temperatrue09" class="form-label">09시:</label>
          <input type="number" class="form-control" id="temperatrue09" placeholder="09시 시간" name="temperatrue09" autocomplete="off" v-model="result.temperature09" />
        </div>
        <div class="mb-3">
          <label for="temperatrue13" class="form-label">13시:</label>
          <input type="number" class="form-control" id="temperatrue13" placeholder="13시 시간" name="temperatrue13" autocomplete="off" v-model="result.temperature13" /> 
        </div>
        <div class="mb-3">
          <label for="temperatrue17" class="form-label">17시:</label>
          <input type="number" class="form-control" id="temperatrue17" placeholder="17시 시간 " name="temperatrue17" autocomplete="off" v-model="result.temperature17" />
        </div>
        <div class="mb-3">
          <label for="name" class="form-label">점검자 이름:</label>
          <input type="text" class="form-control" id="name" placeholder="이름은 한번만 저장" name="name" autocomplete="off" v-model="result.name" />
        </div>
        <div class="d-flex">
          <div class="p-2 flex-fill">
            <div class="form-check">
            </div>
          </div>
          <div class="p-2 flex-fill">
            <div class="form-check">
            </div>
          </div>
        </div>
      </form>
      <div class="d-flex">
        <div class="p-2 flex-fill d-grid">
          <button type="button" class="btn btn-primary" @click="edit">수정</button>
        </div>
        <div class="p-2 flex-fill d-grid">
          <button type="button" class="btn btn-primary" @click="del">삭제</button>
        </div>
        <div class="p-2 flex-fill d-grid">
          <button type="button" class="btn btn-primary" @click="cancel">취소</button>
        </div>
      </div>
    </div>
  </template>

<script>
import axios from 'axios'
export default {
  name:'SaveView',
  data(){
    return {
      result: {}
    }
  },
  created(){
    console.log('수정',this.$store.state.user)
    this.result = this.$store.state.user
  },
  methods: {
    edit() {
      axios
      .post('http://localhost:8084/api/editById', this.result)
      .then((res) => {
        console.log(res)
        if(res.data.state){
          this.$store.commit('setUser',this.result)
          this.cancel()
        }else{
          alert(res.data.message)
        }
      })
      .catch((err) => console.log(err))
    },
    del() {
      const params = { params: { id: this.result.id } }
      axios
        .delete('http://localhost:8084/api/delete', params)
        .then((res) => {
          if(res.data.state){
            this.cancel()
          }else{
            alert(res.data.message)
          }
        })
        .catch((err) => console.log(err))
    },
    cancel() {
      this.$store.commit('setUser', {});
      this.$router.push({ name: 'ListView' });
    }
  }
}
</script>