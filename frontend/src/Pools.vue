
<template>
    <div class="container-fluid">
        <div class="page-header">
            <h1>Pool Statistics</h1>
        </div>
        {{ poolNames }}
    </div>
</template>

<script>

export default {
    name: 'Pool Statistics',
    data() {
        return {
            poolStats: {},
        }
    },
    computed: {
        poolNames() {
            const prefix = 'vertx.pools.worker.';
            const suffix = '.usage'
            const names = [];
            for (let key of Object.keys(this.poolStats)) {
                if (key.startsWith(prefix) && key.endsWith(suffix)) {
                    names.push(key.substring(prefix.length, key.length - suffix.length));
                }
            }
            return names;
        }
    },
    beforeMount() {
        const updateData = () => this.$http.get(window.location.pathname + '/pools')
            .then(response => response.json())
            .then(stats => this.poolStats = stats);
        updateData();
        this.updateTask = setInterval(updateData, 1000);
    },
    beforeDestroy() {
        clearInterval(this.updateTask);
    }
}
</script>