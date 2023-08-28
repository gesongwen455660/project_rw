const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 8080,
    host: '0.0.0.0',
    https: false,
    open: true,
    proxy:'http://192.168.40.243:9090'
  },
  css: {
    loaderOptions: {
      less: {
        lessOptions: {
          // 其他 less 配置项...
        }
      }
    }
  }
})
