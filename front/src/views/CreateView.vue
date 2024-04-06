<template>
  <div class="container mt-3">
    <h1 class="display-1 text-center">온도 생성</h1>
    <div class="camera-buttons">
      <button @click="captureFromCamera">웹캠</button>
    </div>
    <form>
      <div class="mb-3 mt-3">
        <label for="temperature09" class="form-label">09시:</label>
        <input type="text" class="form-control" id="inputField09" placeholder="09시 시간" name="temperature09" autocomplete="off" v-model="result.temperature09" :style="{ borderColor: active.temperature09 ? 'blue' : '' }" />
      </div>
      <div class="mb-3">
        <label for="temperature13" class="form-label">13시:</label>
        <input type="text" class="form-control" id="inputField13" placeholder="13시 시간" name="temperature13" autocomplete="off" v-model="result.temperature13" :style="{ borderColor: active.temperature13 ? 'blue' : '' }" /> 
      </div>
      <div class="mb-3">
        <label for="temperature17" class="form-label">17시:</label>
        <input type="text" class="form-control" id="inputField17" placeholder="17시 시간 " name="temperature17" autocomplete="off" v-model="result.temperature17" :style="{ borderColor: active.temperature17 ? 'blue' : '' }" />
      </div>
      <div class="mb-3">
        <label for="name" class="form-label">점검자:</label>
        <input type="text" class="form-control" id="name" placeholder="이름은 한번만 저장" name="name" autocomplete="off" v-model="result.name" :style="{ borderColor: active.name ? 'blue' : '' }" />
      </div>
    </form>
    <div class="d-flex">
      <div class="p-2 flex-fill d-grid">
        <button type="button" class="btn btn-primary" @click="save">등록</button>
      </div>
      <div class="p-2 flex-fill d-grid">
        <router-link to="/list" class="btn btn-primary">취소</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import Tesseract from 'tesseract.js';

export default {
  name: 'CreateView',
  data() {
    return {
      result: {
        temperature09: '',
        temperature13: '',
        temperature17: '',
        name: ''
      },
      active: {
        temperature09: false,
        temperature13: false,
        temperature17: false,
        name: false
      }
    }
  },
  methods: {
    captureFromCamera() {
      const video = document.createElement('video');
      const canvas = document.createElement('canvas');
      const ctx = canvas.getContext('2d');


      navigator.mediaDevices.getUserMedia({ video: true })
        .then(stream => {
          video.srcObject = stream;
          video.play();
        })
        .catch(error => {
          console.error('웹캠을 시작하는 데 문제가 발생했습니다.', error);
        });

      video.addEventListener('loadedmetadata', () => {
        canvas.width = video.videoWidth;
        canvas.height = video.videoHeight;


        ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
        canvas.toBlob(blob => {
          const file = new File([blob], 'capture.png');
          this.readImage(file);
        }, 'image/png');
      });
    },
    readImage(file) {
      Tesseract.recognize(
        file,
        'kor',
        { logger: m => console.log(m) }
      ).then(({ data: { text } }) => {
        console.log(text);
        const numbers = text.match(/\d+/g);
        if (numbers && numbers.length >= 1) {
          // 현재 시간을 기준으로 가장 가까운 시간대 찾기
          const currentHour = new Date().getHours();
          let closestHour;
          if (currentHour < 9) closestHour = 9;
          else if (currentHour < 13) closestHour = 13;
          else if (currentHour < 17) closestHour = 17;
          else closestHour = 9; // 다음날 9시로
          
          switch (closestHour) {
            case 9:
              this.result.temperature09 = numbers[0];
              break;
            case 13:
              this.result.temperature13 = numbers[0];
              break;
            case 17:
              this.result.temperature17 = numbers[0];
              break;
            default:
              console.error('가장 가까운 시간대를 찾을 수 없습니다.');
          }
        } else {
          console.error('추출부족.');
        }
      });
    },
    save() {
      const date = new Date(this.result.date);

      axios
        .put('http://localhost:8084/api/save', this.result)
        .then((res) => {
          console.log(res)
          this.$store.commit('setUser', res.data.result)
          this.$router.push({ name: 'ListView' })
        })
        .catch((err) => console.log(err))
    }
  }
}
</script>

<style scoped>
.active {
  background-color: blanchedalmond;
}
</style>