<template>
    <div class="task-item">
        <input v-model="item.title" @change="updateItem" />
        <button @click="toggleImportance">{{ item.isImportant ? '★' : '☆' }}</button>
        <button @click="deleteItem">Delete</button>
    </div>
</template>

<script>
export default {
    name: 'TaskItem',
    props: ['item'],
    methods: {
        updateItem() {
            // Enviar atualização do item para o back-end
            this.$emit('updateItem', this.item);
        },
        toggleImportance() {
            this.item.isImportant = !this.item.isImportant;
            this.updateItem();
        },
        deleteItem() {
            this.$emit('deleteItem', this.item.id);
        }
    }
}
</script>