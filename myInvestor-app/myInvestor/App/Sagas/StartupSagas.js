/*
 * @Author: mwk 
 * @Date: 2017-08-10 12:25:31 
 * @Last Modified by: mwk
 * @Last Modified time: 2017-08-12 10:20:38
 */
import { put, select } from "redux-saga/effects";
import { is } from "ramda";
import AnalyticsActions from "../Redux/AnalyticsRedux";

// process STARTUP actions
export function* startup(action) {
  if (__DEV__ && console.tron) {
    // straight-up string logging
    console.tron.log("myInvestor startup saga.");
  }
  yield put(AnalyticsActions.getMarketsRequest());
}
