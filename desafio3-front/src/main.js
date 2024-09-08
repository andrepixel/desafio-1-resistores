import { createApp } from 'vue';
import App from './App.vue';
import { createRouter, createWebHistory } from 'vue-router';
import TaskList from './components/TaskList.vue'; 

const routes = [
  { path: '/', component: TaskList },             
  { path: '/:id', component: TaskList } 
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

createApp(App).use(router).mount('#app');
