<template>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
        <div class="card-pf card-pf-view">
            <div class="card-pf-body">
                <h2 class="card-pf-title pool-name">{{ pool.name }}</h2>
            </div>
            {{ pool }}
        </div>
    </div>
</template>

<script>
export default {
    props: {
        pool: Object,
        greatestSize: Number
    }
}
</script>

<style lang="scss" scoped>
</style>