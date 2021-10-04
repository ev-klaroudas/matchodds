package klaroudas.evangelos.match_odds.model;

import org.springframework.data.repository.CrudRepository;

/** MatchRepository class implements CRUD operations for MatchEntity
 * @see MatchEntity
 * 
 * @author baggelis
 *
 */
public interface MatchRepository extends CrudRepository <MatchEntity, Long> {

}
