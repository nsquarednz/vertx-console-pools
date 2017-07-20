<template>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="card-pf card-pf-view">
            <div class="card-pf-body">
                <div class="data-row">
                    <h2 class="card-pf-title pool-name">{{ pool.name }}</h2>
                    <div class="pool-type">{{ pool.type.toUpperCase() }}</div>
                </div>
                <div class="pf-card-separator" />
                <usage-map class="usage" :used="pool.inUse" :max="pool.maxSize" :largest="greatestSize" :queued="pool.queueSize" />
                <div class="pf-card-separator" />
                <div class="row rates">
                    <div class="col-sm-12 col-md-6 rate-col">
                        <h2 class="card-pf-title">Tasks Run</h2>
                        <table class="details-table" style="display: inline-block;">
                            <tbody>
                                <tr>
                                    <td class="cell-k">Mean</td>
                                    <td class="cell-v">{{ abbreviate(pool.usage.meanRate.toFixed(2), 1) }}/s</td>
                                </tr>
                                <tr>
                                    <td class="cell-k">1 min</td>
                                    <td class="cell-v">{{ abbreviate(pool.usage.oneMinuteRate.toFixed(2), 1) }}/s</td>
                                </tr>
                                <tr>
                                    <td class="cell-k">5 min</td>
                                    <td class="cell-v">{{ abbreviate(pool.usage.fiveMinuteRate.toFixed(2), 1) }}/s</td>
                                </tr>
                                <tr>
                                    <td class="cell-k">15 min</td>
                                    <td class="cell-v">{{ abbreviate(pool.usage.fifteenMinuteRate.toFixed(2), 1) }}/s</td>
                                </tr>
                            </tbody>
                        </table>
                        <h2 class="card-pf-title">Exec Time</h2>
                        <table class="details-table">
                            <tbody>
                                <tr>
                                    <td class="cell-k">Mean</td>
                                    <td class="cell-v">{{ prettyMs(pool.usage.mean) }}</td>
                                </tr>
                                <tr>
                                    <td class="cell-k">50th</td>
                                    <td class="cell-v">{{ prettyMs(pool.usage.median) }}</td>
                                </tr>
                                <tr>
                                    <td class="cell-k">95th</td>
                                    <td class="cell-v">{{ prettyMs(pool.usage['95%']) }}</td>
                                </tr>
                                <tr>
                                    <td class="cell-k">99th</td>
                                    <td class="cell-v">{{ prettyMs(pool.usage['99%']) }}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-sm-12 col-md-6 rate-col">
                        <h2 class="card-pf-title">Queue adds</h2>
                        <table class="details-table" style="display: inline-block;">
                            <tbody>
                                <tr>
                                    <td class="cell-k">Mean</td>
                                    <td class="cell-v">{{ abbreviate(pool.queueDelay.meanRate.toFixed(2), 1) }}/s</td>
                                </tr>
                                <tr>
                                    <td class="cell-k">1 min</td>
                                    <td class="cell-v">{{ abbreviate(pool.queueDelay.oneMinuteRate.toFixed(2), 1) }}/s</td>
                                </tr>
                                <tr>
                                    <td class="cell-k">5 min</td>
                                    <td class="cell-v">{{ abbreviate(pool.queueDelay.fiveMinuteRate.toFixed(2), 1) }}/s</td>
                                </tr>
                                <tr>
                                    <td class="cell-k">15 min</td>
                                    <td class="cell-v">{{ abbreviate(pool.queueDelay.fifteenMinuteRate.toFixed(2), 1) }}/s</td>
                                </tr>
                            </tbody>
                        </table>
                        <h2 class="card-pf-title">Wait Time</h2>
                        <table class="details-table" style="display: inline-block;">
                            <tbody>
                                <tr>
                                    <td class="cell-k">Mean</td>
                                    <td class="cell-v">{{ prettyMs(pool.queueDelay.mean) }}</td>
                                </tr>
                                <tr>
                                    <td class="cell-k">50th</td>
                                    <td class="cell-v">{{ prettyMs(pool.queueDelay.median) }}</td>
                                </tr>
                                <tr>
                                    <td class="cell-k">95th</td>
                                    <td class="cell-v">{{ prettyMs(pool.queueDelay['95%']) }}</td>
                                </tr>
                                <tr>
                                    <td class="cell-k">99th</td>
                                    <td class="cell-v">{{ prettyMs(pool.queueDelay['99%']) }}</td>
                                </tr>
                            </tbody>
                        </table>
    
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import abbreviate from 'number-abbreviate';
import prettyMs from 'pretty-ms';
import UsageMap from './UsageMap.vue';

export default {
    props: {
        pool: Object,
        greatestSize: Number
    },
    components: {
        'usage-map': UsageMap
    },
    beforeMount() {
        this.abbreviate = abbreviate;
        this.prettyMs = prettyMs;
        console.log(abbreviate(3.21232132, 1));
    }
}
</script>

<style lang="scss" scoped>
.pool-name {
    font-size: 14px;
    margin: 0;
}

.pool-type {
    float: right;
    font-weight: bold;
}

.pool-name,
.pool-type {
    display: inline-block;
}

.card-pf {
    padding: 0;
    border-left: none;
    border-right: none;
}

.card-pf-body {
    margin-top: 15px;
    .data-row,
    .usage,
    .rates {
        margin: 0 15px;
    }
}

.rates {
    .rate-col {
        padding: 0;

        &:last-child {
            padding-left: 5px;
        }
    }

    .card-pf-title {
        font-size: 14px;
        margin: 5px 0 7px;
    }
}

.card-pf-body .pf-card-separator {
    height: 1px;
    background: #e1e1e1;
    margin: 15px 0;
}

.details-table {
    border-top: 1px solid #f1f1f1;
    width: 80%;

    .cell-k {
        font-weight: bold;
        min-width: 47px;
        padding: 1px 8px;
        padding-left: 0;
        border-right: 1px solid #f1f1f1;
    }

    .cell-v {
        padding: 1px 8px;
    }
}
</style>