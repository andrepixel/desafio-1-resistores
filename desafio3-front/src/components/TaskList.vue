<template>
    <div class="task-list">
        <h3>
            <input v-model="title" @change="updateTitle" />
            <button @click="deleteList">Delete</button>
        </h3>

        <div v-for="item in items" :key="item.id">
            <router-link :to="'/' + item.id">
                <TaskItem :item="item" @updateItem="updateItem" @deleteItem="deleteItem" />
            </router-link>
        </div>
        <button @click="addItem">Add Item</button>
    </div>
</template>

<script>
import TaskItem from './TaskItem.vue';

export default {
    name: 'TaskList',
    components: {
        TaskItem,
    },
    props: ['list'],
    data() {
        return {
            title: this.list.title,
            items: this.list.items,
        };
    },
    methods: {
        updateTitle() {
            // Enviar atualização do título para o back-end
            this.$emit('updateTitle', this.title);
        },
        deleteList() {
            // Enviar requisição para deletar lista
            this.$emit('deleteList', this.list.id);
        },
        addItem() {
            // Adicionar item à lista
            const newItem = { id: Date.now(), title: '', isImportant: false };
            this.items.push(newItem);
            this.$emit('addItem', newItem);
        },
        updateItem(item) {
            // Atualizar item no back-end
            this.$emit('updateItem', item);
        },
        deleteItem(itemId) {
            // Deletar item no back-end
            this.items = this.items.filter(item => item.id !== itemId);
            this.$emit('deleteItem', itemId);
        }
    }
}
</script>