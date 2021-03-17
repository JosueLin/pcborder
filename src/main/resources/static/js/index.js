/*
 * @Descripttion: 
 * @version: 
 * @Author: YoungLetter
 * @Date: 2021-03-14 10:57:02
 * @LastEditors: Youngletter
 * @LastEditTime: 2021-03-17 13:12:49
 */
/* 
增加功能：
1. 截取size字符串
2. 将px 自动转化为rem
*/
// 设置正则
let regObj = {
  name: /^[\u4E00-\u9FA5A-Za-z0-9_]{2,6}$/,
  number: /^[0-9]{10}$/,
  academy: /^\u4fe1\u606f\u5de5\u7a0b\u5b66\u9662$/,
  level: /^[1-2]{1}$/
}
// 获取元素
let number = my$('number')
let name = my$('name')
let academy = my$('academy')
let weChat = my$('we-chat')
let size = my$('size')
let level = my$('level')
// 发送的参数
let info = {
  number,
  name,
  academy,
  weChat,
  size,
  level
}
// 监听select
level.onchange = function () {
  if (level.value == '2') {
    question.style.display = 'block'
  } else {
    question.style.display = 'none'
  }
}
// 获取成功、失败、询问
let success = document.querySelector('.success')
let failure = document.querySelector('.failure')
let question = document.querySelector('.question')
let buttons = question.getElementsByTagName('button')
buttons[0].onclick = function () {
  question.style.display = 'none'
}
buttons[1].onclick = function () {
  level.value = '1'
  question.style.display = 'none'
}
// 监听发送请求的按钮
document.querySelector('button').addEventListener('click', testInformation)

function my$(id) {
  return document.getElementById(id)
}
// 将对象转化为query格式
function dataStringify(data) {
  let str = ''
  for (let key in data) {
    str = str + key + "=" + data[key] + "&"
  }
  str = str.substr(0, str.length - 1)
  return str
}
// 网络请求相关代码
function request(config) {
  const instance = axios.create({
    baseURL: 'http://localhost:9090',
    timeout: 20000
  })
  return instance(config)
}

// 发送个人信息
function sendInformation(info) {
  return request({
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    method: 'post',
    url: '/order',
    data: dataStringify({
      studentID: info.number.value,
      studentName: info.name.value,
      college: info.academy.value,
      weChat: info.weChat.value,
      sizeA: info.size.value.slice(0, info.size.value.indexOf('*', 0)),
      sizeB: info.size.value.substr(info.size.value.indexOf('*', 0) + 1),
      numberOfLayers: info.level.value,
    }),
  })
}
// 校验个人信息
function testInformation() {
  if (window.timer) {
    // 当频繁点击，要记得删除定时器
    clearInterval(window.timer)
  }
  this.disabled = true
  // 进行初步验证，如果错误，直接不发送请求
  // flag 用于判断输入格式
  let flag = true
  // 初步判断是否为空
  for (let key in info) {
    if (info[key].value === '') {
      flag = false
      break
    }
  }
  // 对 学号长度、学院格式、名称、层级进行判断
  for (let key in regObj) {
    if (!regObj[key].test(info[key].value)) {
      flag = false
      break
    }
  }
  // 对大小格式进行初步验证
  if (info.size.value.indexOf('*', 0) == -1) {
    flag = false
  }
  // 对定时器使用了箭头函数，此时this指向不再是window，因此要注意window绑定的属性与该元素的绑定的属性
  if (flag) {
    sendInformation(info).then((res) => {
      if(res.data.result == "true") {
        // 预约成功，显示预约成功界面，三秒后刷新界面
        success.style.display = 'block'
        success.querySelector('.content').innerHTML = res.data.message|| '预约成功'
        window.timer = setInterval(() => {
          success.style.display = 'none'
          history.go(0)
          clearInterval(window.timer)
        }, 2000)
      } else {
        failure.style.display = 'block'
        failure.querySelector('.content').innerHTML = res.data.message
        // 预约失败，显示预约失败界面，三秒后隐藏
        window.timer = setInterval(() => {
          failure.style.display = 'none'
          this.disabled = false
          clearInterval(window.timer)
        }, 2000)
      }
    }).catch((res) => {
      failure.style.display = 'block'
      failure.querySelector('.content').innerHTML = '预约失败'
      // 预约失败，显示预约失败界面，三秒后隐藏
      window.timer = setInterval(() => {
        failure.style.display = 'none'
        this.disabled = false
        clearInterval(window.timer)
      }, 2000)
    })
  } else {
    if (info.academy.value && !regObj.academy.test(info.academy.value)) {
      failure.querySelector('.content').innerHTML = '您没有预约权限'
    } else {
      failure.querySelector('.content').innerHTML = '预约失败(注意格式)'
    }
    failure.style.display = 'block'
    // 预约失败，显示预约失败界面，三秒后隐藏
    window.timer = setInterval(() => {
      failure.style.display = 'none'
      this.disabled = false
      clearInterval(window.timer)
    }, 2000)
  }
}