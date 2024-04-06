const target = 'http://localhost:8084'
module.exports = {
  devServer:{
    port: 8084,
    proxy: {
      'api':{
        target,
        changeOrigin: true
      }
    }

  }
}
