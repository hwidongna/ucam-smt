// Adapted from kenlm code
#ifndef KENLM_UTIL_HPP
#define KENLM_UTIL_HPP

#include <lm/binary_format.hh>
#include <lm/model.hh>

/**
 * \file
 */

namespace ucam {
namespace util {
/**
 * Detects kenlm language model types
 * code recycled from kenlm tool
 */

inline lm::ngram::ModelType detectkenlm (std::string const& kenlmfile) {
  lm::ngram::ModelType model_type;
  if (kenlmfile == "" ) return lm::ngram::PROBING;
  if (lm::ngram::RecognizeBinary (kenlmfile.c_str(), model_type) )
    switch (model_type) {
    case lm::ngram::PROBING:
    case lm::ngram::REST_PROBING:
    case lm::ngram::TRIE:
    case lm::ngram::QUANT_TRIE:
    case lm::ngram::ARRAY_TRIE:
    case lm::ngram::QUANT_ARRAY_TRIE:
      return model_type;
    default:
      LERROR ("Unrecognized kenlm model type " << model_type );
      exit (EXIT_FAILURE);
    }
  return lm::ngram::PROBING;
}

}
} // end namespaces

#endif
